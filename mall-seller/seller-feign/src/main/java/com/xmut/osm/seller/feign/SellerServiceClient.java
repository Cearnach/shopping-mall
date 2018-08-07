package com.xmut.osm.seller.feign;

import com.xmut.osm.common.bean.PageInfo;
import com.xmut.osm.common.constraint.FeignClientConstraints;
import com.xmut.osm.common.util.BaseServiceClient;
import com.xmut.osm.entity.Seller;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 阮胜
 * @date 2018/8/7 16:39
 */
@FeignClient(name = FeignClientConstraints.SELLER_SERVICE_CLIENT_NAME)
public interface SellerServiceClient extends BaseServiceClient<Seller> {

    @GetMapping("/all?page={page}&size={size}")
    @Override
    PageInfo<Seller> fetchAll(@PathVariable("page") Integer page, @PathVariable("size") Integer size);

    @PostMapping("/save")
    @Override
    boolean save(@RequestBody Seller seller);

    @DeleteMapping("/deleteAll")
    @Override
    List<Integer> deleteAll(@RequestParam("ids") Integer[] ids);
}
