package com.xmut.osm.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Url权限
 *
 * @author 阮胜
 * @date 2018/8/7 14:32
 */
@Data
@Entity
public class Permission {
    @Id
    @GeneratedValue
    private Integer id;


    private String url;

    private Integer projectCode;

    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;

    public Permission() {
    }

    public Permission(String url, Integer projectCode, String description, List<Role> roles) {
        this.url = url;
        this.projectCode = projectCode;
        this.description = description;
        this.roles = roles;
    }
}
