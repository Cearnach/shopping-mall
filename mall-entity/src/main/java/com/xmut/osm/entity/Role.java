package com.xmut.osm.entity;

import lombok.Data;

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

    private Integer code;

    private String name;
}
