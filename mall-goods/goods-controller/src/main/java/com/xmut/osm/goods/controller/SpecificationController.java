package com.xmut.osm.goods.controller;

import com.xmut.osm.common.bean.PageInfo;
import com.xmut.osm.entity.Specification;
import com.xmut.osm.form.PageBean;
import com.xmut.osm.goods.service.SpecificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 阮胜
 * @date 2018/7/30 14:58
 */
@RestController
@Slf4j
@RequestMapping("/specification")
public class SpecificationController {
    private final SpecificationService specificationService;

    public SpecificationController(SpecificationService specificationService) {
        this.specificationService = specificationService;
    }

    @GetMapping("/findAll")
    public PageInfo<Specification> all(PageBean pageBean) {
        Page<Specification> specificationPage = specificationService.findAll(pageBean);
        return new PageInfo<>(pageBean.getPage(), specificationPage.getTotalElements(), specificationPage.getContent());
    }
}
