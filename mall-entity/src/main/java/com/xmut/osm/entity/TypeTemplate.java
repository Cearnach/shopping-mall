package com.xmut.osm.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @author 阮胜
 * @date 2018/7/31 14:11
 */
@Data
@Entity
public class TypeTemplate {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    @OneToMany(cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "spec_id")
    private List<Specification> specificationList;

    @OneToMany(cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "brand_id")
    private List<Brand> brandList;

    private String customAttribute;
}
