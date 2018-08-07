package com.xmut.osm.goods.feign;

import com.xmut.osm.common.bean.PageInfo;
import com.xmut.osm.common.util.BaseServiceClient;
import com.xmut.osm.entity.TypeTemplate;
import com.xmut.osm.goods.constraint.FeignClientConstraints;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 阮胜
 * @date 2018/7/31 14:21
 */
@FeignClient(name = FeignClientConstraints.GOODS_SERVICE_CLIENT_NAME)
public interface TypeTemplateClient extends BaseServiceClient<TypeTemplate> {

    @GetMapping("/typeTemplate/all?page={page}&size={size}")
    @Override
    PageInfo<TypeTemplate> fetchAll(@PathVariable("page") Integer page, @PathVariable("size") Integer size);

    @PostMapping("/typeTemplate/save")
    @Override
    boolean save(@RequestBody TypeTemplate typeTemplate);

    @DeleteMapping("/typeTemplate/deleteAll")
    @Override
    List<Integer> deleteAll(Integer[] ids);


}
