package com.xmut.osm.security.vcode.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 验证码基类.如果需要图像验证码什么的 可以继承该类进行扩展
 * @author 阮胜
 * @date 2018/8/3 12:58
 */
@Data
public class VerificationCode  {
    protected String code;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    protected LocalDateTime expireTime;
    protected boolean expired;
    public VerificationCode() {
    }

    public VerificationCode(String code, int expireSeconds) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireSeconds);
    }

    public VerificationCode(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(this.expireTime);
    }
}
