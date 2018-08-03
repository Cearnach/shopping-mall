package com.xmut.osm.security.vcode.processor;



import com.xmut.osm.security.vcode.entity.VerificationCode;
import com.xmut.osm.security.vcode.exception.VerificationModeNotFoundException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

/**
 * @author 阮胜
 * @date 2018/8/3 13:20
 */
public interface VerificationCodeProcessor<C extends VerificationCode> {


    /**
     * 创建验证码
     *
     * @param request 用于将验证码保存到session中
     * @return 返回创建后的验证码实例
     */
    C createAndSend(HttpServletRequest request) throws VerificationModeNotFoundException, MessagingException;

    /**
     * 校验验证码
     *
     * @param code
     * @return
     */
    boolean authenticate(String code);
}
