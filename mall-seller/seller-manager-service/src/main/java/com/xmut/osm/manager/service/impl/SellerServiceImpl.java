package com.xmut.osm.manager.service.impl;

import com.xmut.osm.entity.Seller;
import com.xmut.osm.manager.service.SellerService;
import com.xmut.osm.repository.SellerRepository;
import com.xmut.osm.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 阮胜
 * @date 2018/8/6 20:48
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SellerServiceImpl extends BaseServiceImpl<Seller, Integer, SellerRepository> implements SellerService {
}