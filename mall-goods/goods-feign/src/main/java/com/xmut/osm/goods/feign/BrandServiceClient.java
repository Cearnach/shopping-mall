package com.xmut.osm.goods.feign;

import com.xmut.osm.common.bean.PageInfo;
import com.xmut.osm.entity.Brand;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 阮胜
 * @date 2018/7/22 23:37
 */
@FeignClient(name = "GOODS-SERVICE")
public interface BrandServiceClient {
    @GetMapping("/brand/list?size={size}&page={page}")
    PageInfo<Brand> fetchBrandList(@PathVariable("size") Integer size, @PathVariable("page") Integer page);

    @PostMapping("/brand/save")
    boolean save(@RequestBody Brand brand);

    @DeleteMapping("/brand/deleteAll")
    List<Integer> deleteAll(@RequestParam("ids") Integer[] ids);
}
