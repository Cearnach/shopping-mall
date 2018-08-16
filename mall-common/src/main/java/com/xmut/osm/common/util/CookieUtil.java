package com.xmut.osm.common.util;

import javax.servlet.http.Cookie;

/**
 * @author 阮胜
 * @date 2018/8/16 14:17
 */
public class CookieUtil {
    public static String fetchCookie(Cookie[] cookies, String header) {
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (header.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
