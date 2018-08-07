package com.xmut.osm.common.util;

import com.xmut.osm.common.bean.PageInfo;

import java.util.List;

/**
 * @author 阮胜
 * @date 2018/7/30 15:27
 */
public interface BaseServiceClient<T> {
    PageInfo<T> fetchAll(Integer page, Integer size);

    boolean save(T t);

    List<Integer> deleteAll(Integer[] ids);

}
