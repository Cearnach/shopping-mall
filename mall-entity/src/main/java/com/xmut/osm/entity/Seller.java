package com.xmut.osm.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

/**
 * @author 阮胜
 * @date 2018/7/22 22:39
 */
@Data
@Entity
public class Seller {
    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 公司名称
     */
    @NotBlank
    private String companyName;

    /**
     * 店铺名称
     */
    @NotBlank
    private String storeName;

    /**
     * 店铺账号
     */
    @NotBlank
    private String account;

    /**
     * 店铺密码
     */
    @NotBlank
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;


    /**
     * 公司电话
     */
    @NotBlank
    private String companyPhone;

    private Integer status;

    /**
     * 公司详细地址
     */
    @NotBlank
    private String companyAddressDetail;

    /**
     * 联系人姓名
     */
    @NotBlank
    private String linkmanName;

    /**
     * 联系人QQ
     */
    private String linkmanQq;

    /**
     * 联系人电话
     */
    @NotBlank
    private String linkmanPhone;

    /**
     * 联系人邮箱
     */
    private String linkmanEmail;

    /**
     * 营业执照号
     */
    @NotBlank
    private String licenseNumber;

    /**
     * 税务登记证号
     */
    @NotBlank
    private String taxNumber;

    /**
     * 组织机构代码
     */
    @NotBlank
    private String orgNumber;


    /**
     * 公司LOGO图
     */
    private String logoPic;

    private String description;

    /**
     * 法定代表人
     */
    @NotBlank
    private String legalPerson;

    /**
     * 法定代表人身份证
     */
    @NotBlank
    private String legalPersonCardId;

    /**
     * 开户行账号名称
     */
    private String bankUser;

    /**
     * 开户银行
     */
    private String bankName;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false)
    private Date createDate;

}
