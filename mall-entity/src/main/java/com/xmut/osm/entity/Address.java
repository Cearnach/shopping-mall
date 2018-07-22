package com.xmut.osm.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author 阮胜
 * @date 2018/7/22 21:59
 */
@Data
@Entity
public class Address {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private District district;

    private String phone;

    private String contact;

    /**
     * 详细信息
     */
    private String detailAddress;

    private String isDefault;

    /**
     * 备注
     */
    private String notes;

    private Date createDate;

}
