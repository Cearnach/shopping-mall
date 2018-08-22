package com.xmut.osm.search.service.impl;

import com.xmut.osm.common.bean.PageBean;
import com.xmut.osm.search.service.GoodsSolrService;
import com.xmut.osm.solr.entity.SolrGoods;
import com.xmut.osm.solr.repository.GoodsSolrRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author 阮胜
 * @date 2018/8/22 14:04
 */
@Service
public class GoodsSolrServiceImpl implements GoodsSolrService {
    private final GoodsSolrRepository goodsSolrRepository;

    public GoodsSolrServiceImpl(GoodsSolrRepository goodsSolrRepository) {
        this.goodsSolrRepository = goodsSolrRepository;
    }

    @Override
    public Page<SolrGoods> search(Map<String, String> keyMap, PageBean pageBean) {
        return goodsSolrRepository.findAllByNameOrBrandNameOrItemCategory(keyMap.get("name"),
                keyMap.get("brand"), keyMap.get("cat"), PageRequest.of(pageBean.getPage(), pageBean.getSize()));
    }
}
