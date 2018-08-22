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
        String name = keyMap.get("name");
        String brand = keyMap.get("brand");
        String cat = keyMap.get("cat");
        return goodsSolrRepository.findByNameLikeOrBrandNameLikeOrItemCategoryLike(name == null ? "0" : name,
                brand == null ? "0" : brand, cat == null ? "0" : cat, PageRequest.of(pageBean.getPage(), pageBean.getSize()));
    }

    @Override
    public Page<SolrGoods> findByGoodsNameLike(String goodsName, PageBean pageBean) {
        return goodsSolrRepository.findByNameLike(goodsName,PageRequest.of(pageBean.getPage(),pageBean.getSize()));
    }

    @Override
    public SolrGoods save(SolrGoods solrGoods) {
        return goodsSolrRepository.save(goodsSolrRepository.save(solrGoods));
    }

}
