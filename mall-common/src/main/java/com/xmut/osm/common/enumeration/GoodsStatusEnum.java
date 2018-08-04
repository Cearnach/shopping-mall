package com.xmut.osm.common.enumeration;

import lombok.Getter;

/**
 * @author 阮胜
 * @date 2018/7/22 22:44
 */
@Getter
public enum GoodsStatusEnum {
    /**
     * code=编号,name=描述
     */
    SOLD_ON(10, "已上架"),
    SOLD_OFF(20, "已下架");

    private Integer code;

    private String name;

    GoodsStatusEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
