package com.xmut.osm.goods.service.impl;

import com.xmut.osm.entity.Goods;
import com.xmut.osm.goods.service.GoodsService;
import com.xmut.osm.repository.GoodsRepository;
import com.xmut.osm.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 阮胜
 * @date 2018/7/21 14:52
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class GoodsServiceImpl extends BaseServiceImpl<Goods, String, GoodsRepository> implements GoodsService {

}
