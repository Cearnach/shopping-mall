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

    @ManyToMany(cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
    private List<Specification> specificationList;

    @ManyToMany(cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
    private List<Brand> brandList;

    private String customAttribute;
}
