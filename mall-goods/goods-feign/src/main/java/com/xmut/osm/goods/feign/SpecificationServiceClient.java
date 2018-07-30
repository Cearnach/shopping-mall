package com.xmut.osm.goods.feign;

import com.xmut.osm.common.bean.PageInfo;
import com.xmut.osm.entity.Specification;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 阮胜
 * @date 2018/7/30 15:02
 */
@FeignClient(name = FeignClientConstraint.GOODS_SERVICE_CLIENT_NAME)
public interface SpecificationServiceClient extends BaseServiceClient<Specification> {

    @GetMapping("/specification/all?page={page}&size={size}")
    @Override
    PageInfo<Specification> fetchAll(@PathVariable("page") Integer page, @PathVariable("size") Integer size);

    @PostMapping("/specification/save")
    @Override
    boolean save(@RequestBody Specification specification);

    @DeleteMapping("/specification/deleteAll")
    @Override
    List<Integer> deleteAll(@RequestParam("ids") Integer[] ids);
}
