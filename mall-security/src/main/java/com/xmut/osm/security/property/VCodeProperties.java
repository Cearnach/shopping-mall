package com.xmut.osm.security.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 阮胜
 * @version 1.0
 * @date 2018/6/12 14:51
 */
@Data
@Component
@ConfigurationProperties(prefix = "com.xmut.osm.security.verification")
public class VCodeProperties {
    /**
     * 从前端传过来的验证码模式(比如邮箱验证,手机短信验证)
     */
    private String mode = "vmode";
    /**
     * 过期时间(单位:秒)
     */
    private int expireSeconds = 60 * 10;
    /**
     * 验证码放入session时的前缀
     */
    private String sessionKeyPrefix = "SESSION_KEY_FOR_CODE_";
}
