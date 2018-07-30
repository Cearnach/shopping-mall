package com.xmut.osm.goods.feign;

import com.xmut.osm.common.bean.PageInfo;
import com.xmut.osm.entity.Brand;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author 阮胜
 * @date 2018/7/30 15:27
 */
public interface BaseServiceClient<T> {
    PageInfo<T> fetchAll(Integer page, Integer size);

    boolean save(@RequestBody Brand brand);

    List<Integer> deleteAll(@RequestParam("ids") Integer[] ids);

}
