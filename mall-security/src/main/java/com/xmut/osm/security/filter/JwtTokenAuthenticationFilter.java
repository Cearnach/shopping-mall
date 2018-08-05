package com.xmut.osm.security.filter;

import com.xmut.osm.security.property.JwtAuthenticationProperties;
import com.xmut.osm.security.util.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 该过滤器用于验证用户权限
 *
 * @author 阮胜
 * @date 2018/8/4 14:28
 */
@Slf4j
public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {

    /**
     * 用户权限列表
     */
    private static final String AUTHORITIES = "authorities";
    private final JwtAuthenticationProperties jwtAuthenticationProperties;

    public JwtTokenAuthenticationFilter(JwtAuthenticationProperties jwtAuthenticationProperties) {
        this.jwtAuthenticationProperties = jwtAuthenticationProperties;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        String token = fetchCookie(cookies);
        if (StringUtils.isEmpty(token)) {
            token = request.getHeader(jwtAuthenticationProperties.getHeader());
        }
        if (!StringUtils.isEmpty(token) && token.startsWith(jwtAuthenticationProperties.getPrefix())) {
            String jwtToken = token.replace(jwtAuthenticationProperties.getPrefix(), "");
            try {
                Claims claims = JwtTokenUtil.parse(jwtToken, jwtAuthenticationProperties.getSecret());
                System.out.println(claims);
                String username = claims.getSubject();
                @SuppressWarnings("unchecked")
                List<String> authorities = claims.get(AUTHORITIES, List.class);
                if (!StringUtils.isEmpty(username) && !CollectionUtils.isEmpty(authorities)) {
                    List<SimpleGrantedAuthority> grantedAuthorityList = authorities.stream()
                            .map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toList());
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username,
                            null, grantedAuthorityList);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (Exception e) {
                log.error(e.getMessage());
                SecurityContextHolder.clearContext();
            }
        }
        filterChain.doFilter(request, response);
    }

    private String fetchCookie(Cookie[] cookies) {
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (jwtAuthenticationProperties.getHeader().equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
