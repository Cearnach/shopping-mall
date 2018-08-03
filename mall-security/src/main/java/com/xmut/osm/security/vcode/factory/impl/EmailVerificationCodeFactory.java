package com.xmut.osm.security.vcode.factory.impl;


import com.xmut.osm.security.property.VCodeProperties;
import com.xmut.osm.security.vcode.entity.EmailVerificationCode;
import com.xmut.osm.security.vcode.factory.AbstractVerificationCodeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author 阮胜
 * @date 2018/8/3 13:44
 */
@Component
public class EmailVerificationCodeFactory implements AbstractVerificationCodeFactory<EmailVerificationCode> {
    @Autowired
    private VCodeProperties vCodeProperties;

    @Override
    public EmailVerificationCode generate() {
        EmailVerificationCode emailVerificationCode = new EmailVerificationCode();
        emailVerificationCode.setCode(UUID.randomUUID().toString());
        emailVerificationCode.setExpireTime(LocalDateTime.now().plusSeconds(vCodeProperties.getExpireSeconds()));
        return emailVerificationCode;
    }
}
