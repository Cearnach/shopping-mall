package com.xmut.osm.goods.service;

import com.xmut.osm.dto.GoodsDTO;
import com.xmut.osm.entity.Goods;
import com.xmut.osm.exception.TargetEntityNotFound;
import com.xmut.osm.service.base.BaseService;

import java.util.Optional;

/**
 * @author 阮胜
 * @date 2018/7/21 14:52
 */
public interface GoodsService extends BaseService<Goods, String> {
    Goods save(GoodsDTO goodsDTO) throws TargetEntityNotFound;
}
