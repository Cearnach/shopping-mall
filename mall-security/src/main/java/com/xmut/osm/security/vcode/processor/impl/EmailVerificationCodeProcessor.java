package com.xmut.osm.security.vcode.processor.impl;

import com.xmut.osm.security.util.EmailUtil;
import com.xmut.osm.security.vcode.entity.VerificationCode;
import com.xmut.osm.security.vcode.manager.VerificationCodeFactoryManager;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import javax.mail.MessagingException;

/**
 * @author 阮胜
 * @date 2018/8/3 14:54
 */
@Component
public class EmailVerificationCodeProcessor extends AbstractVerificationCodeProcessor {
    private final EmailUtil emailUtil;
    private final JavaMailSender javaMailSender;

    public EmailVerificationCodeProcessor(VerificationCodeFactoryManager verificationCodeFactoryManager, EmailUtil emailUtil, JavaMailSender javaMailSender) {
        super(verificationCodeFactoryManager);
        this.emailUtil = emailUtil;
        this.javaMailSender = javaMailSender;
    }

    @Override
    protected void sendVerificationCode(ServletWebRequest request, VerificationCode vcode) throws MessagingException {
        String mailTo = request.getAttribute("mailTo", RequestAttributes.SCOPE_REQUEST).toString();
        String account = request.getParameter("account");
        emailUtil.sendVCode(javaMailSender, mailTo, vcode.getCode(), account);
    }
}
