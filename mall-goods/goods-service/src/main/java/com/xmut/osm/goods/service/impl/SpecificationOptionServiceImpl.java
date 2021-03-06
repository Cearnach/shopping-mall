package com.xmut.osm.goods.service.impl;

import com.xmut.osm.entity.SpecificationOption;
import com.xmut.osm.goods.service.SpecificationOptionService;
import com.xmut.osm.repository.SpecificationOptionRepository;
import com.xmut.osm.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 阮胜
 * @date 2018/7/30 14:26
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SpecificationOptionServiceImpl extends BaseServiceImpl<SpecificationOption, Integer, SpecificationOptionRepository>
        implements SpecificationOptionService {

    @Override
    public List<SpecificationOption> findBySpecificationId(Integer specId) {
        return repository.findBySpecificationId(specId);
    }
}
