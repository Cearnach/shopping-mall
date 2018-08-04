package com.xmut.osm.auth.handler;

import com.xmut.osm.security.property.JwtAuthenticationProperties;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * @author 阮胜
 * @date 2018/8/4 19:42
 */
@Component
@Slf4j
public class JwtAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final JwtAuthenticationProperties jwtAuthenticationProperties;

    public JwtAuthenticationSuccessHandler(JwtAuthenticationProperties jwtAuthenticationProperties) {
        this.jwtAuthenticationProperties = jwtAuthenticationProperties;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("登录成功:{}",authentication.getName());
        Instant now = Instant.now();
        String token = Jwts.builder()
                .setSubject(authentication.getName())
                .claim("authorities", authentication.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plusSeconds(jwtAuthenticationProperties.getExpiration())))
                .signWith(SignatureAlgorithm.HS256, jwtAuthenticationProperties.getSecret().getBytes())
                .compact();
        response.addHeader(jwtAuthenticationProperties.getHeader(), jwtAuthenticationProperties.getPrefix() + " " + token);
    }
}
