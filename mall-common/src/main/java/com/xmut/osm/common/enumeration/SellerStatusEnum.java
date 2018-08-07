package com.xmut.osm.common.enumeration;

import lombok.Getter;

/**
 * @author 阮胜
 * @date 2018/8/7 16:12
 */
@Getter
public enum SellerStatusEnum {
    /**
     * statusCode=代号,statusName=状态
     */
    CHECKED(10, "审核通过"),
    UNCHECKED(11, "未审核"),
    CHECK_DENIED(12, "审核未通过"),
    CLOSED(13, "已关闭");
    private Integer statusCode;
    private String statusName;

    SellerStatusEnum(Integer statusCode, String statusName) {
        this.statusCode = statusCode;
        this.statusName = statusName;
    }
}
