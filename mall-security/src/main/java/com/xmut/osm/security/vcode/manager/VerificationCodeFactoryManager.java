package com.xmut.osm.security.vcode.manager;


import com.xmut.osm.security.vcode.factory.AbstractVerificationCodeFactory;

/**
 * 实现对每个验证码生成器的管理调度
 * @author 阮胜
 * @date 2018/8/3 13:20
 */
public interface VerificationCodeFactoryManager {
    AbstractVerificationCodeFactory findVerificationCodeFactory(String factoryType);
}
