package com.xmut.osm.solr.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

/**
 * @author 阮胜
 * @date 2018/8/20 20:35
 */
@Data
@SolrDocument(collection = "new_core")
public class SolrGoods {
    @Id
    @Indexed(name = "item_goods_id")
    private String id;

    @Indexed(name = "item_goods_name", type = "text_ik")
    private String name;

    @Indexed(name = "item_goods_brand_name", type = "text_ik")
    private String brandName;

    @Indexed(name = "item_seller_name", type = "text_ik")
    private String sellerName;

    @Indexed(name = "item_goods_price")
    private Double price;

    @Indexed(name = "item_goods_category_id")
    private Integer categoryId;

    @Indexed(name = "item_goods_category_name")
    private String categoryName;

    @Indexed(name = "item_goods_image")
    private String image;


    @Indexed(name = "item_goods_stock_count")
    private Integer stockCount;

}
