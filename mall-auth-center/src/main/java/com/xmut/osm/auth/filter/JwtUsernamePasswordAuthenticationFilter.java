package com.xmut.osm.auth.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xmut.osm.security.property.JwtAuthenticationProperties;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * @author 阮胜
 * @date 2018/8/4 15:53
 */
public class JwtUsernamePasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private final JwtAuthenticationProperties jwtAuthenticationProperties;
    private final ObjectMapper mapper;
    public static final String POST = "POST";
    public JwtUsernamePasswordAuthenticationFilter(JwtAuthenticationProperties jwtAuthenticationProperties, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(jwtAuthenticationProperties.getLoginUrl(), "POST"));
        this.setAuthenticationManager(authManager);
        this.jwtAuthenticationProperties = jwtAuthenticationProperties;
        this.mapper = new ObjectMapper();
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse rsp)
            throws AuthenticationException, IOException {
        User u = mapper.readValue(req.getInputStream(), User.class);
        return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(
                u.getUsername(), u.getPassword(), Collections.emptyList()
        ));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse rsp, FilterChain chain,
                                            Authentication auth) {
        Instant now = Instant.now();
        String token = Jwts.builder()
                .setSubject(auth.getName())
                .claim("authorities", auth.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plusSeconds(jwtAuthenticationProperties.getExpiration())))
                .signWith(SignatureAlgorithm.HS256, jwtAuthenticationProperties.getSecret().getBytes())
                .compact();
        rsp.addHeader(jwtAuthenticationProperties.getHeader(), jwtAuthenticationProperties.getPrefix() + " " + token);
    }

    @Getter
    @Setter
    private static class User {
        private String username, password;
    }
}
