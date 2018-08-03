package com.xmut.osm.security.vcode.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.awt.image.BufferedImage;

/**
 * 图形验证码
 *
 * @author 阮胜
 * @date 2018/8/3 13:09
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ImageVerificationCode extends VerificationCode {
    private BufferedImage image;
}
