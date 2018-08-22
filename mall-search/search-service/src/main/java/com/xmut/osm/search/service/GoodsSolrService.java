package com.xmut.osm.search.service;

import com.xmut.osm.common.bean.PageBean;
import com.xmut.osm.solr.entity.SolrGoods;
import org.springframework.data.domain.Page;

import java.util.Map;

/**
 * @author 阮胜
 * @date 2018/8/22 14:01
 */
public interface GoodsSolrService {
    Page<SolrGoods> search(Map<String, String> keyMap, PageBean pageBean);
}
