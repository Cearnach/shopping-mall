package com.xmut.osm.security.vcode.manager;


import com.xmut.osm.security.vcode.processor.VerificationCodeProcessor;

/**
 * @author 阮胜
 * @date 2018/8/3 22:12
 */
public interface VerificationCodeProcessorManager {
    VerificationCodeProcessor findVerificationCodeFactory(String proType);
}
