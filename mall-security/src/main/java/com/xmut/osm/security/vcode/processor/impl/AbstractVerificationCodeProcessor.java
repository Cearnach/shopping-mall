package com.xmut.osm.security.vcode.processor.impl;

import com.xmut.osm.security.property.VCodeProperties;
import com.xmut.osm.security.vcode.entity.VerificationCode;
import com.xmut.osm.security.vcode.exception.VerificationModeNotFoundException;
import com.xmut.osm.security.vcode.manager.VerificationCodeFactoryManager;
import com.xmut.osm.security.vcode.processor.VerificationCodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.ServletWebRequest;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

/**
 * 验证码处理器抽象类
 *
 * @author 阮胜
 * @date 2018/8/3 13:59
 */
@Component
public abstract class AbstractVerificationCodeProcessor implements VerificationCodeProcessor {

    private final VerificationCodeFactoryManager verificationCodeFactoryManager;
    @Autowired
    private VCodeProperties vCodeProperties;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public AbstractVerificationCodeProcessor(VerificationCodeFactoryManager verificationCodeFactoryManager) {
        this.verificationCodeFactoryManager = verificationCodeFactoryManager;
    }

    @Override
    public VerificationCode createAndSend(HttpServletRequest request) throws VerificationModeNotFoundException, MessagingException {
        String vmode = generate(request);
        VerificationCode vcode = verificationCodeFactoryManager.findVerificationCodeFactory(vmode).generate();
        saveVerificationCode(request, vcode);
        sendVerificationCode(new ServletWebRequest(request), vcode);
        return vcode;
    }

    private String generate(HttpServletRequest request) throws VerificationModeNotFoundException {
        String vmode = request.getParameter(vCodeProperties.getMode());
        if (StringUtils.isEmpty(vmode)) {
            throw new VerificationModeNotFoundException();
        }
        return vmode;
    }


    /**
     * 保存验证码到redis中
     *
     * @param request
     * @param vcode
     */
    private void saveVerificationCode(HttpServletRequest request, VerificationCode vcode) {
        String vmode = request.getParameter(vCodeProperties.getMode());
        String key = vCodeProperties.getSessionKeyPrefix().concat(vmode);
        String account = request.getParameter("account");
        redisTemplate.opsForHash().put(key, account, vcode);
    }

    /**
     * 发送验证码,具体的发送方式由子类实现
     * @param request
     * @param vcode
     * @throws MessagingException
     */
    protected abstract void sendVerificationCode(ServletWebRequest request, VerificationCode vcode) throws MessagingException;

    /**
     * 校验验证码(暂时不用)
     *
     * @param code
     * @return
     */
    @Override
    public boolean authenticate(String code) {
        return false;
    }
}
