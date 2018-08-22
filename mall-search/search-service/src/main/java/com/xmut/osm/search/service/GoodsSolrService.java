package com.xmut.osm.search.service;

import com.xmut.osm.common.bean.PageBean;
import com.xmut.osm.solr.entity.SolrGoods;
import org.springframework.data.domain.Page;

/**
 * @author 阮胜
 * @date 2018/8/22 14:01
 */
public interface GoodsSolrService {
    Page<SolrGoods> search(SolrGoods solrGoods, PageBean pageBean);

    Page<SolrGoods> findByGoodsNameLike(String goodsName, PageBean pageBean);

    Page<SolrGoods> findByKeyWords(String keywords, PageBean pageBean);

    SolrGoods save(SolrGoods solrGoods);
}
