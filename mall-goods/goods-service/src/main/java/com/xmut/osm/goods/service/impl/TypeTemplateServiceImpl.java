package com.xmut.osm.goods.service.impl;

import com.xmut.osm.entity.TypeTemplate;
import com.xmut.osm.goods.service.TypeTemplateService;
import com.xmut.osm.repository.TypeTemplateRepository;
import com.xmut.osm.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 阮胜
 * @date 2018/7/31 14:19
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TypeTemplateServiceImpl extends BaseServiceImpl<TypeTemplate, Integer, TypeTemplateRepository>
        implements TypeTemplateService {
}
