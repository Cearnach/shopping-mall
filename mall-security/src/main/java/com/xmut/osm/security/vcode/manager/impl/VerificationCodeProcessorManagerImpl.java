package com.xmut.osm.security.vcode.manager.impl;

import com.xmut.osm.security.vcode.manager.VerificationCodeProcessorManager;
import com.xmut.osm.security.vcode.processor.VerificationCodeProcessor;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author 阮胜
 * @date 2018/8/3 22:13
 */
@Component
public class VerificationCodeProcessorManagerImpl implements VerificationCodeProcessorManager {
    private static final String PROCESSOR_SUFFIX = VerificationCodeProcessor.class.getSimpleName();

    /**
     * 收集系统中所有的 {@link VerificationCodeProcessor} 接口的实现。
     */
    private final Map<String, VerificationCodeProcessor> verificationCodeProcessor;

    public VerificationCodeProcessorManagerImpl(Map<String, VerificationCodeProcessor> verificationCodeProcessor) {
        this.verificationCodeProcessor = verificationCodeProcessor;
    }


    @Override
    public VerificationCodeProcessor findVerificationCodeFactory(String proType) {
        return verificationCodeProcessor.get(proType.concat(PROCESSOR_SUFFIX));
    }
}
