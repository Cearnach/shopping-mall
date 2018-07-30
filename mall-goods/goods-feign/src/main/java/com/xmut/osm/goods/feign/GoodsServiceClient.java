package com.xmut.osm.goods.feign;

import com.xmut.osm.entity.Goods;
import com.xmut.osm.goods.constraint.FeignClientConstraints;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 商品业务
 *
 * @author 阮胜
 * @date 2018/7/21 14:56
 */
@FeignClient(name = FeignClientConstraints.GOODS_SERVICE_CLIENT_NAME, fallback = GoodsServiceClient.GoodsServiceClientFallback.class)
public interface GoodsServiceClient {

    @GetMapping("/goods/{goodsId}")
    Goods fetchgoods(@PathVariable("goodsId") String goodsId);

    @Slf4j
    @Component
    class GoodsServiceClientFallback implements GoodsServiceClient {

        @Override
        public Goods fetchgoods(String goodsId) {
            return null;
        }
    }
}
