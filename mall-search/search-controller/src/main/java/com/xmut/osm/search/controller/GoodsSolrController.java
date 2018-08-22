package com.xmut.osm.search.controller;

import com.xmut.osm.common.bean.PageBean;
import com.xmut.osm.common.bean.ResultVO;
import com.xmut.osm.search.service.GoodsSolrService;
import com.xmut.osm.solr.entity.SolrGoods;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author 阮胜
 * @date 2018/8/22 14:37
 */
@RestController
@Slf4j
public class GoodsSolrController {
    private final GoodsSolrService goodsSolrService;

    public GoodsSolrController(GoodsSolrService goodsSolrService) {
        this.goodsSolrService = goodsSolrService;
    }

    @GetMapping("/search")
    public ResultVO<List<SolrGoods>> search(@RequestParam Map<String, String> keyMap, PageBean pageBean) {
        System.out.println(keyMap);
        System.out.println(pageBean);
        Page<SolrGoods> goodsPage = goodsSolrService.search(keyMap, pageBean);
        return new ResultVO<>(null, true, goodsPage.getContent());
    }

    @GetMapping("/find")
    public List<SolrGoods> query(String name,PageBean pageBean) {
        return goodsSolrService.findByGoodsNameLike(name,pageBean).getContent();
    }

    @PostMapping("/save")
    public boolean save(SolrGoods solrGoods) {
        goodsSolrService.save(solrGoods);
        return true;
    }
}
