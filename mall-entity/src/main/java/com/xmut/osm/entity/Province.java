package com.xmut.osm.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;

/**
 * @author 阮胜
 * @date 2018/7/22 12:42
 */
@Data
@Entity
public class Province {
    @Id
    @GeneratedValue
    @Column(name = "province_id")
    private Integer id;

    @Column(name = "province_code", unique = true)
    private Integer code;

    @Column(name = "province_name", unique = true)
    private String name;

    @ManyToOne
    private State state;

    public Province() {
    }

    public Province(@Min(0) Integer code, String name, State state) {

        this.name = name;
        this.code = code;
        this.state = state;
    }
}
