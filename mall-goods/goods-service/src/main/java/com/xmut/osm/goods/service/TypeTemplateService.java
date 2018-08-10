package com.xmut.osm.goods.service;

import com.xmut.osm.dto.TypeTemplateDTO;
import com.xmut.osm.entity.TypeTemplate;
import com.xmut.osm.service.base.BaseService;

import java.util.Optional;

/**
 * @author 阮胜
 * @date 2018/7/31 14:18
 */
public interface TypeTemplateService extends BaseService<TypeTemplate, Integer> {
    TypeTemplate save(TypeTemplateDTO typeTemplateDTO);
}
