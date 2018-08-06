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

    private static final String POST = "POST";
    private final JwtAuthenticationProperties jwtAuthenticationProperties;
    private boolean postOnly = true;

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
        if (postOnly && !POST.equals(request.getMethod())) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }

        String username = obtainUsername(request);
        String password = obtainPassword(request);

        if (username == null) {
            username = "";
        }

        if (password == null) {
            password = "";
        }

        username = username.trim();
        UsernamePasswordAuthenticationToken authRequest;
        if (jwtAuthenticationProperties.getUserLoginUrl().equals(request.getRequestURI())) {
            authRequest = new UsernamePasswordAuthenticationToken(
                    username, password);
        } else {
            authRequest = new SellerUsernamePasswordAuthenticationToken(username, password);
        }

        // Allow subclasses to set the "details" property
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
        cookie.setDomain("ruan.mshome.net");
        cookie.setPath("/");
        response.addCookie(cookie);
        try {
            String success = "登录成功";
            log.info(success + " 用户名:{},权限:{}", auth.getName(), auth.getAuthorities());
            ResultVO<String> resultVO = new ResultVO<>();
            resultVO.setSuccess(true);
            resultVO.setMessage(success);
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
        resultVO.setMessage("用户名或者密码错误");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(JSON.toJSONString(resultVO));
        writer.close();
    }
}
