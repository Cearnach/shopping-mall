package com.xmut.osm.goods.controller;

import com.xmut.osm.entity.Goods;
import com.xmut.osm.goods.service.GoodsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 商品控制层
 * @author 阮胜
 * @date 2018/7/21 15:55
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {
    private final GoodsService goodsService;

    public GoodsController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @GetMapping("/all")
    public List<Goods> all() {
        ArrayList<Goods> goodsList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Goods goods = new Goods();
            goods.setId(UUID.randomUUID().toString());
            goods.setName(String.valueOf(i));
            goodsList.add(goods);
        }
        return goodsList;
    }

    @GetMapping("/{id}")
    public Goods fetchGoods(@PathVariable("id") String id) {
        Goods goods = new Goods();
        goods.setId(id);
        goods.setName("test goods");
        throw new RuntimeException("test");
    }

}
