package com.xmut.osm.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 规格
 *
 * @author 阮胜
 * @date 2018/7/30 14:14
 */
@Data
@Entity
public class Specification implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(unique = true)
    @NotBlank
    private String name;

    public Specification(String name) {
        this.name = name;
    }

    public Specification() {

    }
}
