package com.xmut.osm.search.service.impl;

import com.xmut.osm.common.bean.PageBean;
import com.xmut.osm.search.service.GoodsSolrService;
import com.xmut.osm.solr.entity.SolrGoods;
import com.xmut.osm.solr.repository.GoodsSolrRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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
    public Page<SolrGoods> search(SolrGoods solrGoods, PageBean pageBean) {
        if (solrGoods.getName() == null) {
            solrGoods.setName("");
        }
        if (solrGoods.getBrandName() == null) {
            solrGoods.setBrandName("");
        }
        if (solrGoods.getCategoryName() == null) {
            solrGoods.setCategoryName("");
        }
        return goodsSolrRepository.findByNameLikeOrBrandNameLikeOrCategoryNameLike(solrGoods.getName(), solrGoods.getBrandName(),
                solrGoods.getCategoryName(), PageRequest.of(pageBean.getPage(), pageBean.getSize()));
    }

    @Override
    public Page<SolrGoods> findByGoodsNameLike(String goodsName, PageBean pageBean) {
        return goodsSolrRepository.findByNameLike(goodsName, PageRequest.of(pageBean.getPage(), pageBean.getSize()));
    }

    @Override
    public Page<SolrGoods> findByKeyWords(String keywords, PageBean pageBean) {
        return goodsSolrRepository.findByKeyWords(keywords, PageRequest.of(pageBean.getPage(), pageBean.getSize()));
    }

    @Override
    public SolrGoods save(SolrGoods solrGoods) {
        return goodsSolrRepository.save(goodsSolrRepository.save(solrGoods));
    }

}
