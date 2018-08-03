package com.xmut.osm.security.vcode.processor.impl;

import com.xmut.osm.security.vcode.entity.VerificationCode;
import com.xmut.osm.security.vcode.manager.VerificationCodeFactoryManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author 阮胜
 * @date 2018/8/3 14:54
 */
@Slf4j
@Component
public class ImageVerificationCodeProcessor extends AbstractVerificationCodeProcessor {

    public ImageVerificationCodeProcessor(VerificationCodeFactoryManager verificationCodeFactoryManager) {
        super(verificationCodeFactoryManager);
    }

    @Override
    protected void sendVerificationCode(ServletWebRequest request, VerificationCode vcode) {
        String account = request.getParameter("account");
        log.info("图片验证码处理器:account={},vcode={}", account, vcode.getCode());
    }
}
