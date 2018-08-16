package com.xmut.osm.goods.feign;

import com.xmut.osm.common.bean.PageInfo;
import com.xmut.osm.common.constraint.FeignClientConstraints;
import com.xmut.osm.common.util.BaseServiceClient;
import com.xmut.osm.dto.GoodsDTO;
import com.xmut.osm.entity.Goods;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品业务
 *
 * @author 阮胜
 * @date 2018/7/21 14:56
 */
@FeignClient(name = FeignClientConstraints.GOODS_SERVICE_CLIENT_NAME)
public interface GoodsServiceClient extends BaseServiceClient<Goods> {

    @GetMapping("/goods/all?page={page}&size={size}")
    @Override
    PageInfo<Goods> fetchAll(@PathVariable("page") Integer page, @PathVariable("size") Integer size);

    @PostMapping("/goods/save")
    @Override
    boolean save(@RequestBody Goods goods);

    @PostMapping("/goods/saveDTO")
    boolean save(@RequestBody GoodsDTO goodsDTO);

    @DeleteMapping("/goods/deleteAll")
    @Override
    List<Integer> deleteAll(@RequestParam("ids") Integer[] ids);
}
