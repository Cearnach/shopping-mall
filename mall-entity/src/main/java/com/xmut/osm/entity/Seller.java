package com.xmut.osm.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

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
    private String companyName;

    /**
     * 店铺名称
     */
    private String storeName;

    private String password;

    private String email;

    /**
     * 公司电话
     */
    private String mobile;

    /**
     * 公司手机
     */
    private String phone;

    private String status;

    /**
     * 详细地址
     */
    private String addressDetail;

    /**
     * 联系人姓名
     */
    private String linkmanName;

    /**
     * 联系人QQ
     */
    private String linkmanQq;

    /**
     * 联系人电话
     */
    private String linkmanMobile;

    /**
     * 联系人邮箱
     */
    private String linkmanEmail;

    /**
     * 营业执照号
     */
    private String licenseNumber;

    /**
     * 税务登记证号
     */
    private String taxNumber;

    /**
     * 组织机构代码
     */
    private String orgNumber;

    /**
     * 公司地址
     */
    private Long address;

    /**
     * 公司LOGO图
     */
    private String logoPic;

    private String description;

    /**
     * 法定代表人
     */
    private String legalPerson;

    /**
     * 法定代表人身份证
     */
    private String legalPersonCardId;

    /**
     * 开户行账号名称
     */
    private String bankUser;

    /**
     * 开户行
     */
    private String bankName;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false)
    private Date createDate;

}
