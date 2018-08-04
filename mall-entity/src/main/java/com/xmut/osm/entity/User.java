package com.xmut.osm.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 阮胜
 * @date 2018/7/22 22:03
 */
@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "tb_user")
public class User implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private String password;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 账号
     */
    private String account;

    private String phone;

    private String email;

    /**
     * 真是姓名
     */
    private String name;

    /**
     * 用户状态(是否被冻结等),使用UserStatusEnum
     */
    private Integer status;

    private String headPicture;

    private String qq;

    /**
     * 用户余额
     */
    private BigDecimal balance;

    private String gender;

    @ManyToOne
    private Role role;

    /**
     * 用户等级
     */
    private Integer level;

    /**
     * 用户年龄
     */
    private Integer age;

    /**
     * 积分
     */
    private Integer points;

    /**
     * 经验值
     */
    private Integer experience;

    private Date birthday;

    private Date lastLoginTime;

    private Date updateDate;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false)
    private Date createDate;
}
