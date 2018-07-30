package com.xmut.osm.goods.service.impl;

import com.xmut.osm.entity.Specification;
import com.xmut.osm.goods.service.SpecificationService;
import com.xmut.osm.repository.SpecificationRepository;
import com.xmut.osm.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author 阮胜
 * @date 2018/7/30 14:25
 */
@Service
public class SpecificationServiceImpl extends BaseServiceImpl<Specification, Integer, SpecificationRepository>
        implements SpecificationService {
}
