package com.xmut.osm.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 规格
 *
 * @author 阮胜
 * @date 2018/7/30 14:14
 */
@Data
@Entity
public class Specifiction {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;

}
