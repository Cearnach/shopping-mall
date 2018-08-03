package com.xmut.osm.security.vcode.factory;


import com.xmut.osm.security.vcode.entity.VerificationCode;

/**
 * 每个验证码生成器都需要实现该抽象工厂
 * @author 阮胜
 * @date 2018/8/3 13:20
 */
public interface AbstractVerificationCodeFactory<C extends VerificationCode> {
    /**
     * 生成验证码
     * @return
     */
    C generate();
}
