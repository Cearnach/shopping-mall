package com.xmut.osm.common.enumeration;

import lombok.Getter;

/**
 * @author 阮胜
 * @date 2018/7/22 22:13
 */
@Getter
public enum UserStatusEnum {
    /**
     * code=编号,name=描述
     */
    NORMAL(10, "正常使用"),
    FROZEN(20, "账号被冻结");
    private Integer code;
    private String name;

    UserStatusEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
