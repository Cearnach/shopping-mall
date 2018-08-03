package com.xmut.osm.security.vcode.exception;

/**
 * @author 阮胜
 * @date 2018/8/3 14:35
 */
public class VerificationModeNotFoundException extends Exception{
    private static final String VERIFICATION_MODE_NOT_FOUND = "无法找到指定的验证模式";

    public VerificationModeNotFoundException() {
        super(VERIFICATION_MODE_NOT_FOUND);
    }
}
