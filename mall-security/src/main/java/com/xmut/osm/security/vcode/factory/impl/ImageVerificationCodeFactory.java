package com.xmut.osm.security.vcode.factory.impl;


import com.xmut.osm.security.vcode.entity.ImageVerificationCode;
import com.xmut.osm.security.vcode.factory.AbstractVerificationCodeFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author 阮胜
 * @date 2018/8/3 13:44
 */
@Component
@Slf4j
public class ImageVerificationCodeFactory implements AbstractVerificationCodeFactory<ImageVerificationCode> {

    @Override
    public ImageVerificationCode generate() {
        log.info("图像验证码生成");
        ImageVerificationCode imageVerificationCode = new ImageVerificationCode();
        imageVerificationCode.setCode("image");
        imageVerificationCode.setExpireTime(LocalDateTime.now().plusSeconds(60 * 10));
        return imageVerificationCode;
    }
}
