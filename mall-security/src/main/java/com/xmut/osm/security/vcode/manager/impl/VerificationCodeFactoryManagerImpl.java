package com.xmut.osm.security.vcode.manager.impl;

import com.xmut.osm.security.vcode.factory.AbstractVerificationCodeFactory;
import com.xmut.osm.security.vcode.manager.VerificationCodeFactoryManager;
import org.springframework.stereotype.Component;

import java.util.Map;


/**
 * @author 阮胜
 * @date 2018/8/3 13:43
 */
@Component
public class VerificationCodeFactoryManagerImpl implements VerificationCodeFactoryManager {
    /**
     * 工厂后缀名
     */
    private static final String FACTORY_SUFFIX = "VerificationCodeFactory";

    /**
     * 收集系统中所有的 {@link AbstractVerificationCodeFactory} 接口的实现。
     */
    private final Map<String, AbstractVerificationCodeFactory> verificationCodeFactory;

    public VerificationCodeFactoryManagerImpl(Map<String, AbstractVerificationCodeFactory> verificationCodeFactory) {
        this.verificationCodeFactory = verificationCodeFactory;
    }


    @Override
    public AbstractVerificationCodeFactory findVerificationCodeFactory(String factoryType) {
        return verificationCodeFactory.get(factoryType.concat(FACTORY_SUFFIX));
    }
}
