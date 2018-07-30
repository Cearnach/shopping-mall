package com.xmut.osm.goods.controller;

import com.xmut.osm.common.bean.PageInfo;
import com.xmut.osm.entity.Brand;
import com.xmut.osm.entity.Specification;
import com.xmut.osm.form.PageBean;
import com.xmut.osm.goods.service.BrandService;
import com.xmut.osm.goods.service.SpecificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/save")
    public boolean save(@RequestBody Specification specification) {
        specificationService.save(specification);
        return true;
    }

    @DeleteMapping("/deleteAll")
    public List<Integer> deleteAll(Integer[] ids) {
        return specificationService.deleteIn(ids);
    }
}
