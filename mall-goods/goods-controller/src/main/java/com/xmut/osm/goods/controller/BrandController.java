package com.xmut.osm.goods.controller;

import com.xmut.osm.entity.Brand;
import com.xmut.osm.form.PageBean;
import com.xmut.osm.goods.service.BrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public List<Brand> fetchBrandList(PageBean pageBean) {
        return brandService.findAll(pageBean).getContent();
    }
}
