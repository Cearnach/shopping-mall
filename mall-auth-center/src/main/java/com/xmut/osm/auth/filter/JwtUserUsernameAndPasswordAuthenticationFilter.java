package com.xmut.osm.auth.filter;

import com.alibaba.fastjson.JSON;
import com.xmut.osm.auth.token.SellerUsernamePasswordAuthenticationToken;
import com.xmut.osm.common.bean.ResultVO;
import com.xmut.osm.security.property.JwtAuthenticationProperties;
import com.xmut.osm.security.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

/**
 * 用户名密码验证过滤器
 *
 * @author 阮胜
 * @date 2018/8/4 15:53
 */
@Slf4j
public class JwtUserUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final String AUTHENTICATION_METHOD_NOT_SUPPORTED = "身份验证方法不支持: ";
    private static final String USERNAME_OR_PASSWORD_CANNOT_BE_NULL = "用户名或密码不能为空";
    private static final String POST = "POST";
    private static final String LOGIN_SUCCESS = "登录成功";
    private static final String ERROR_USERNAME_OR_PASSWORD = "用户名或者密码错误";
    private static final String CONTENT_TYPE_JSON = "application/json;charset=utf-8";
    private final JwtAuthenticationProperties jwtAuthenticationProperties;

    public JwtUserUsernameAndPasswordAuthenticationFilter(String loginUrl, JwtAuthenticationProperties jwtAuthenticationProperties,
                                                          AuthenticationManager authenticationManager) {
        this.jwtAuthenticationProperties = jwtAuthenticationProperties;
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(loginUrl,
                POST));
        this.setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        if (!POST.equals(request.getMethod())) {
            throw new AuthenticationServiceException(
                    AUTHENTICATION_METHOD_NOT_SUPPORTED.concat(request.getMethod()));
        }

        String username = request.getParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY);
        String password = request.getParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY);

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new RuntimeException(USERNAME_OR_PASSWORD_CANNOT_BE_NULL);
        }
        UsernamePasswordAuthenticationToken authRequest;
        //通过登录的URL来决定使用哪种Token. Token类型会在 UserDetailsAuthenticationProvider 的 supports 方法种进行校验.
        if (jwtAuthenticationProperties.getUserLoginUrl().equals(request.getRequestURI())) {
            authRequest = new UsernamePasswordAuthenticationToken(
                    username, password);
        } else {
            authRequest = new SellerUsernamePasswordAuthenticationToken(username, password);
        }
        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication auth) {

        String token = JwtTokenUtil.generate(
                auth.getName(),
                auth.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority).collect(Collectors.toList()),
                jwtAuthenticationProperties.getExpiration(),
                jwtAuthenticationProperties.getSecret()
        );
        Cookie cookie = new Cookie(jwtAuthenticationProperties.getHeader(), jwtAuthenticationProperties.getPrefix().concat(token));
        cookie.setMaxAge(60 * 60 * 24 * 7);
        cookie.setPath("/");
        response.addCookie(cookie);
        try {
            log.info(LOGIN_SUCCESS.concat(" 用户名:{},权限:{}"), auth.getName(), auth.getAuthorities());
            ResultVO<String> resultVO = new ResultVO<>();
            resultVO.setSuccess(true);
            resultVO.setMessage(LOGIN_SUCCESS);
            response.setContentType("application/json;charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.write(JSON.toJSONString(resultVO));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        ResultVO<String> resultVO = new ResultVO<>();
        resultVO.setSuccess(false);
        resultVO.setMessage(ERROR_USERNAME_OR_PASSWORD);
        response.setContentType(CONTENT_TYPE_JSON);
        PrintWriter writer = response.getWriter();
        writer.write(JSON.toJSONString(resultVO));
        writer.close();
    }
}
