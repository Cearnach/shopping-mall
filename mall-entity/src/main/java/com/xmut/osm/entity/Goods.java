package com.xmut.osm.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

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

    private Double price;

    @ManyToOne
    private GoodsType goodsType;

    private Boolean deleted;

    /**
     * 商品状态
     */
    private Integer status;

    private String image;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updateDate;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false)
    private Date createDate;
}
