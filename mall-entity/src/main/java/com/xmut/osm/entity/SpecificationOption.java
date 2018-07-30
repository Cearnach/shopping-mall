package com.xmut.osm.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

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
    @NotBlank
    private String name;
    @ManyToOne
    @JoinColumn(name = "specification_id")
    private Specification specification;
    @Column(name = "option_order")
    @Min(0)
    private Integer order;

    public SpecificationOption() {
    }

    public SpecificationOption(String name, Specification specification, Integer order) {

        this.name = name;
        this.specification = specification;
        this.order = order;
    }
}
