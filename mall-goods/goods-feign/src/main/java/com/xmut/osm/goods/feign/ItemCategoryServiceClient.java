package com.xmut.osm.goods.feign;

import com.xmut.osm.common.bean.PageInfo;
import com.xmut.osm.common.constraint.FeignClientConstraints;
import com.xmut.osm.common.util.BaseServiceClient;
import com.xmut.osm.entity.ItemCategory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 阮胜
 * @date 2018/8/15 21:43
 */
@FeignClient(name = FeignClientConstraints.GOODS_SERVICE_CLIENT_NAME)
public interface ItemCategoryServiceClient extends BaseServiceClient<ItemCategory> {
    @GetMapping("/itemCat/all?page={page}&size={size}")
    @Override
    PageInfo<ItemCategory> fetchAll(@PathVariable("page") Integer page, @PathVariable("size") Integer size);

    @PostMapping("/itemCat/save")
    @Override
    boolean save(@RequestBody ItemCategory itemCategory);

    @DeleteMapping("/itemCat/deleteAll")
    @Override
    List<Integer> deleteAll(@RequestParam("ids") Integer[] ids);
}
