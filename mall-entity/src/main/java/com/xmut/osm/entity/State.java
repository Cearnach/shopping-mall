package com.xmut.osm.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * @author 阮胜
 * @date 2018/7/22 12:46
 */
@Data
@Entity
public class State {
    @Id
    @GeneratedValue
    @Column(name = "state_id")
    private Integer id;

    @Column(name = "state_code", unique = true)
    private Integer code;

    @NotBlank
    @Column(name = "state_name", unique = true)
    private String name;

    public State() {
    }

    public State(@Min(0) Integer code, @NotBlank String name) {

        this.code = code;
        this.name = name;
    }
}
