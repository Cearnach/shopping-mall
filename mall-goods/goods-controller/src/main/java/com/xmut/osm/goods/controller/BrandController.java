package com.xmut.osm.goods.controller;

import com.xmut.osm.common.bean.PageInfo;
import com.xmut.osm.entity.Brand;
import com.xmut.osm.form.PageBean;
import com.xmut.osm.goods.service.BrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 阮胜
 * @date 2018/7/23 0:31
 */
@RestController
@RequestMapping("/brand")
@Slf4j
public class BrandController {
    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/list")
    public PageInfo<Brand> fetchBrandList(PageBean pageBean) {
        Page<Brand> brandPage = brandService.findAll(pageBean);
        return new PageInfo<>(pageBean.getPage(), brandPage.getTotalElements(), brandPage.getContent());
    }

    @PostMapping("/save")
    public boolean save(@RequestBody Brand brand) {
        brandService.save(brand);
        return true;
    }

    @DeleteMapping("/deleteAll")
    public List<Integer> deleteAll(Integer[] ids) {
        return brandService.deleteIn(ids);
    }
}
