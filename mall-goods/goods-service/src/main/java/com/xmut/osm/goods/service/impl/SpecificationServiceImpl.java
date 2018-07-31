package com.xmut.osm.goods.service.impl;

import com.xmut.osm.dto.SpecificationDTO;
import com.xmut.osm.entity.Specification;
import com.xmut.osm.goods.service.SpecificationService;
import com.xmut.osm.repository.SpecificationOptionRepository;
import com.xmut.osm.repository.SpecificationRepository;
import com.xmut.osm.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 阮胜
 * @date 2018/7/30 14:25
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SpecificationServiceImpl extends BaseServiceImpl<Specification, Integer, SpecificationRepository>
        implements SpecificationService {
    private final SpecificationOptionRepository specificationOptionRepository;

    public SpecificationServiceImpl(SpecificationOptionRepository specificationOptionRepository) {
        this.specificationOptionRepository = specificationOptionRepository;
    }

    @Override
    public Specification save(SpecificationDTO specificationDTO) {
        Specification specification = repository.save(repository.findById(specificationDTO.getId())
                .orElse(new Specification(specificationDTO.getName())));
        //获取规格选项id集合
        List<Integer> optionIdList = specificationDTO.getSpecificationOptionList().stream()
                .map(specificationOption -> {
                    specificationOption.setSpecification(specification);
                    if (StringUtils.isEmpty(specificationOption.getName()) || specificationOption.getOrder() == null) {
                        return 0;
                    }
                    return specificationOptionRepository.save(specificationOption).getId();
                }).filter(id -> id != 0)
                .collect(Collectors.toList());
        //查找出需要删除的optionId
        List<Integer> deleteIdList = specificationOptionRepository
                .findBySpecificationIdAndIdNotIn(specificationDTO.getId(), optionIdList);
        if (!CollectionUtils.isEmpty(deleteIdList)) {
            specificationOptionRepository.deleteAll(deleteIdList);
        }

        return specification;
    }
}
