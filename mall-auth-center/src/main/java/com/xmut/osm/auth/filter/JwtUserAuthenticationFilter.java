package com.xmut.osm.auth.filter;

import com.xmut.osm.security.property.JwtAuthenticationProperties;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Instant;
import java.util.Date;
import java.util.stream.Collectors;

public class JwtUserAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final String POST = "POST";
    private final JwtAuthenticationProperties jwtAuthenticationProperties;

    public JwtUserAuthenticationFilter(JwtAuthenticationProperties jwtAuthenticationProperties, AuthenticationManager authenticationManager) {
        this.jwtAuthenticationProperties = jwtAuthenticationProperties;
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(jwtAuthenticationProperties.getLoginUrl(), POST));
        this.setAuthenticationManager(authenticationManager);
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

}
