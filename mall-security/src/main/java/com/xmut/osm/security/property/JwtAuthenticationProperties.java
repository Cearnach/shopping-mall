package com.xmut.osm.security.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 阮胜
 * @date 2018/8/4 13:58
 */
@Data
@Component
@ConfigurationProperties(prefix = "com.xmut.osm.security.jwt")
public class JwtAuthenticationProperties {

    /**
     * 默认登录地址
     */
    private String loginUrl = "/login";

    /**
     * 头名称
     */
    private String header = "Authorization";

    /**
     * Authorization 字符串的起始字符
     */
    private String prefix = "ruan";

    /**
     * 过期时间 单位:秒
     */
    private int expiration = 60 * 60 * 24 * 7;

    private String secret;
}
