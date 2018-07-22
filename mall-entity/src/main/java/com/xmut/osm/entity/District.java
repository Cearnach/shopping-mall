package com.xmut.osm.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;


/**
 * @author 阮胜
 * @date 2018/7/22 12:43
 */
@Data
@Entity
public class District {
    @Id
    @GeneratedValue
    @Column(name = "district_id")
    private Integer id;

    @Column(name = "district_code", unique = true)
    private Integer code;

    @NotBlank
    @Column(name = "district_name")
    private String name;


    @ManyToOne
    private City city;

    public District() {
    }

    public District(@Min(0) Integer code, @NotBlank String name, City city) {

        this.code = code;
        this.name = name;
        this.city = city;
    }
}
