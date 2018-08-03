package com.xmut.osm.security.encoder;

import com.xmut.osm.security.util.MD5Util;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * MD5加密处理
 * 由于前台已经使用了MD5加密,所以后台无需再对密码进行处理
 *
 * @author 阮胜
 * @date 2018/8/3 15:16
 */
public class Md5PasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        return MD5Util.encript(rawPassword.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encode(rawPassword).equals(encodedPassword);
    }
}
