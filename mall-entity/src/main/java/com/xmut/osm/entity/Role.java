package com.xmut.osm.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author 阮胜
 * @date 2018/7/22 22:08
 */
@Data
@Entity
public class Role {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique = true)
    private Integer code;

    @Column(unique = true)
    private String name;

    public Role(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
