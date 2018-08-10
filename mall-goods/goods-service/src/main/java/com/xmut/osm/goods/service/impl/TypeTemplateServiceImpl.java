package com.xmut.osm.goods.service.impl;

import com.xmut.osm.dto.TypeTemplateDTO;
import com.xmut.osm.entity.Brand;
import com.xmut.osm.entity.Specification;
import com.xmut.osm.entity.TypeTemplate;
import com.xmut.osm.goods.service.TypeTemplateService;
import com.xmut.osm.repository.BrandRepository;
import com.xmut.osm.repository.SpecificationRepository;
import com.xmut.osm.repository.TypeTemplateRepository;
import com.xmut.osm.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author 阮胜
 * @date 2018/7/31 14:19
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TypeTemplateServiceImpl extends BaseServiceImpl<TypeTemplate, Integer, TypeTemplateRepository>
        implements TypeTemplateService {
    private final BrandRepository brandRepository;
    private final SpecificationRepository specificationRepository;

    public TypeTemplateServiceImpl(BrandRepository brandRepository, SpecificationRepository specificationRepository) {
        this.brandRepository = brandRepository;
        this.specificationRepository = specificationRepository;
    }


    @Override
    public TypeTemplate save(TypeTemplateDTO typeTemplateDTO) {
        List<Integer> brandIdList = typeTemplateDTO.getBrandIdList();
        List<Brand> brandList = null;
        if (!CollectionUtils.isEmpty(brandIdList)) {
            brandList = brandIdList.stream()
                    .filter(brandId -> brandId != null && brandId > 0)
                    .map(brandId -> brandRepository.findById(brandId).orElse(null))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        }
        List<Integer> specificationIdList = typeTemplateDTO.getSpecificationIdList();
        List<Specification> specificationList = null;
        if (!CollectionUtils.isEmpty(specificationIdList)) {
            specificationList = specificationIdList.stream()
                    .filter(specId -> specId != null && specId > 0)
                    .map(specId -> specificationRepository.findById(specId).orElse(null))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        }
        TypeTemplate typeTemplate = new TypeTemplate();
        typeTemplate.setBrandList(brandList);
        typeTemplate.setSpecificationList(specificationList);
        typeTemplate.setCustomAttribute(typeTemplateDTO.getCustomAttribute());
        typeTemplate.setName(typeTemplateDTO.getName());
        return repository.save(typeTemplate);
    }
}
