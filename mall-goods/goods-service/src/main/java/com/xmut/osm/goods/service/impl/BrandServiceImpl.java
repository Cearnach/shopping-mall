package com.xmut.osm.goods.service.impl;

import com.xmut.osm.entity.Brand;
import com.xmut.osm.goods.service.BrandService;
import com.xmut.osm.repository.BrandRepository;
import com.xmut.osm.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 阮胜
 * @date 2018/7/22 23:27
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BrandServiceImpl extends BaseServiceImpl<Brand, Integer, BrandRepository> implements BrandService {
}
