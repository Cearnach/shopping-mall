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
public class SpecificationOption {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "specification_id")
    private Specification specification;
    @Column(name = "option_order")
    private Integer order;
}
