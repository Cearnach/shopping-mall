package com.xmut.osm.enumeration;

import lombok.Getter;

/**
 * @author 阮胜
 * @date 2018/8/4 21:29
 */
@Getter
public enum RoleEnum {
    /**
     * code=编号,name=描述
     */
    ADMIN(100, "超级管理员"),
    USER(200, "普通用户");

    private Integer code;
    private String name;

    RoleEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
