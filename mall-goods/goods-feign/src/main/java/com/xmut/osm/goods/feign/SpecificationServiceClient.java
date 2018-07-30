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
public interface SpecificationServiceClient {
    @GetMapping("/specification/all?page={page}&size={size}")
    PageInfo<Specification> fetchSepecificationAll(@PathVariable("page") Integer page, @PathVariable("size") Integer size);

    @PostMapping("/specification/add")
    boolean add(@RequestBody Specification specification);

    @PutMapping("/specification/update")
    boolean update(@RequestBody Specification specification);

    @DeleteMapping("/specification/deleteAll")
    List<Integer> deleteAll(@RequestParam("ids") Integer[] ids);
}
