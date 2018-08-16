package com.xmut.osm.dto;

import com.xmut.osm.entity.ItemCategory;
import com.xmut.osm.entity.Seller;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @author 阮胜
 * @date 2018/8/16 14:03
 */
@Data
public class GoodsDTO {

    private String id;

    @NotBlank
    private String name;

    private String secondName;

    @Min(1)
    private Integer brandId;

    private String sellerAccount;

    @Min(0)
    private Double price;


    private Boolean deleted;

    private Integer itemCategoryId;

    private String description;

    private Integer status;

    private String image;

    private Date updateDate;

    private Date createDate;
}
