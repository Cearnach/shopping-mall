package com.xmut.osm.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 规格选项
 *
 * @author 阮胜
 * @date 2018/7/30 14:14
 */
@Data
@Entity
public class SpecifictionOption {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "specifiction_id")
    private Specifiction specifiction;
    private Integer order;
}
