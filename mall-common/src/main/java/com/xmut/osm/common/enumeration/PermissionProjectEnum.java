package com.xmut.osm.common.enumeration;

import lombok.Getter;

/**
 * @author 阮胜
 * @date 2018/8/7 14:42
 */
@Getter
public enum PermissionProjectEnum {
    /**
     * code=代号,project=项目名称
     */
    RESOURCE(10, "静态资源"),
    MANAGER(11, "运营商后台"),
    SELLER(12, "卖家");

    private Integer projectCode;
    private String project;

    PermissionProjectEnum(Integer code, String project) {
        this.projectCode = code;
        this.project = project;
    }
}
