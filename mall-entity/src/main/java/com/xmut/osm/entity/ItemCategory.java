package com.xmut.osm.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author 阮胜
 * @date 2018/8/15 21:30
 */
@Data
@Entity
public class ItemCategory {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;

}
