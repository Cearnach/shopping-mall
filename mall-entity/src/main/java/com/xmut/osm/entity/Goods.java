package com.xmut.osm.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author 阮胜
 * @date 2018/7/20 21:29
 */

@Data
@Entity
@DynamicUpdate
@DynamicInsert
public class Goods implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "goods_id")
    private String id;

    @NotBlank
    private String name;

    /**
     * 副标题
     */
    private String secondName;

    @JoinColumn(name = "brand_id")
    @ManyToOne
    private Brand brand;

    @ManyToOne
    private Seller seller;

    @Min(0)
    private Double price;

    @ManyToOne
    private ItemCategory itemCategory;

    private String description;

    /**
     * 商品状态
     */
    private Integer status;

    @ElementCollection(fetch=FetchType.LAZY, //加载策略,延迟加载
            targetClass=String.class) //指定集合中元素的类型
    @CollectionTable(name="goods_images") //指定集合生成的表
    private List<String> images;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updateDate;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false)
    private Date createDate;
}
