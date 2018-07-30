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
@FeignClient(name = FeignClientConstraint.GOODS_SERVICE_CLIENT_NAME)
public interface BrandServiceClient extends BaseServiceClient<Brand> {

    @GetMapping("/brand/all?page={page}&size={size}")
    @Override
    PageInfo<Brand> fetchAll(@PathVariable("page") Integer page, @PathVariable("size") Integer size);

    @PostMapping("/brand/save")
    @Override
    boolean save(@RequestBody Brand brand);

    @DeleteMapping("/brand/deleteAll")
    @Override
    List<Integer> deleteAll(@RequestParam("ids") Integer[] ids);


}
