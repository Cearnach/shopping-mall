package com.xmut.osm.goods.service;

import com.xmut.osm.entity.SpecificationOption;
import com.xmut.osm.service.base.BaseService;

import java.util.List;

/**
 * @author 阮胜
 * @date 2018/7/30 14:26
 */
public interface SpecificationOptionService extends BaseService<SpecificationOption, Integer> {
    List<SpecificationOption> findBySpecificationId(Integer specId);
}
