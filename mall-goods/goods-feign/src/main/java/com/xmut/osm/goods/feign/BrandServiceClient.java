package com.xmut.osm.goods.feign;

import com.xmut.osm.entity.Brand;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author 阮胜
 * @date 2018/7/22 23:37
 */
@FeignClient(name = "GOODS-SERVICE")
public interface BrandServiceClient {
    @GetMapping("/brand/list?size={size}&page={page}")
    List<Brand> fetchBrandList(@PathVariable("size") Integer size, @PathVariable("page") Integer page);
}
