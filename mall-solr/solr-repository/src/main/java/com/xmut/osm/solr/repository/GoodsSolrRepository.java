package com.xmut.osm.solr.repository;

import com.xmut.osm.solr.entity.SolrGoods;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

/**
 * @author 阮胜
 * @date 2018/8/22 13:59
 */
public interface GoodsSolrRepository extends SolrCrudRepository<SolrGoods, String> {
    Page<SolrGoods> findByNameLikeOrBrandNameLikeOrCategoryNameLike(String goodsName, String brandName,
                                                                    String itemCategoryName, Pageable pageable);

    Page<SolrGoods> findByNameLike(String goodsName, Pageable pageable);

    @Query("item_keywords:?0~")
    Page<SolrGoods> findByKeyWords(String keywords,Pageable pageable);
}
