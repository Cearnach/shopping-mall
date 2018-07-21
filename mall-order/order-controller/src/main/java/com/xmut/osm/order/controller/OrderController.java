package com.xmut.osm.order.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.xmut.osm.common.bean.ResultVO;
import com.xmut.osm.entity.Goods;
import com.xmut.osm.goods.feign.GoodsServiceClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 阮胜
 * @date 2018/7/21 18:38
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    private final GoodsServiceClient goodsServiceClient;

    public OrderController(GoodsServiceClient goodsServiceClient) {
        this.goodsServiceClient = goodsServiceClient;
    }

    @GetMapping("/test")
    @HystrixCommand(fallbackMethod = "fallback")
    public ResultVO<Goods> t() {
        Goods goods = goodsServiceClient.fetchgoods("gid");
        ResultVO<Goods> resultVO = new ResultVO<>();
        resultVO.setSuccess(true);
        resultVO.setData(goods);
        return resultVO;
    }

    public ResultVO<Goods>  fallback() {
        ResultVO<Goods> resultVO = new ResultVO<>();
        resultVO.setSuccess(false);
        resultVO.setMessage("failed");
        return resultVO;
    }
}
