package com.xmut.osm.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

/**
 * @author 阮胜
 * @date 2018/8/4 14:35
 */
public class JwtTokenUtil {
    public static Claims parse(String jwtToken, String secret) {
        return Jwts.parser()
                .setSigningKey(secret.getBytes())
                .parseClaimsJws(jwtToken)
                .getBody();
    }
}
