package com.xmut.osm.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * @author 阮胜
 * @date 2018/7/22 12:42
 */
@Data
@Entity
public class City {
    @Id
    @GeneratedValue
    @Column(name = "city_id")
    private Integer id;

    @NotBlank
    @Column(name = "city_name")
    private String name;

    @Column(name = "city_code", unique = true)
    private Integer code;

    @ManyToOne
    private Province province;

    public City() {
    }

    public City(Integer code) {
        this.code = code;
    }

    public City(@Min(0) Integer code, @NotBlank String name, Province province) {

        this.code = code;
        this.name = name;
        this.province = province;
    }
}
