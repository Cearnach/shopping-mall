package com.xmut.osm.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author 阮胜
 * @date 2018/8/4 14:35
 */
public class JwtTokenUtil {

    public static final String AUTHORITIES = "authorities";

    /**
     * 生成JwtToken
     *
     * @return
     */
    public static String generate(String username, List<String> authorities, int expiration, String secret) {
        Instant now = Instant.now();
        return Jwts.builder()
                .setSubject(username)
                .claim(AUTHORITIES, authorities)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plusSeconds(expiration)))
                .signWith(SignatureAlgorithm.HS256, secret.getBytes())
                .compact();
    }

    public static Claims parse(String jwtToken, String secret) {
        return Jwts.parser()
                .setSigningKey(secret.getBytes())
                .parseClaimsJws(jwtToken)
                .getBody();
    }
}
