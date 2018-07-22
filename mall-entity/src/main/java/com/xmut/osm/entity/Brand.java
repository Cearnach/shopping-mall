package com.xmut.osm.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author 阮胜
 * @date 2018/7/22 21:58
 */
@Data
@Entity
public class Brand {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private Character firstChar;
}
