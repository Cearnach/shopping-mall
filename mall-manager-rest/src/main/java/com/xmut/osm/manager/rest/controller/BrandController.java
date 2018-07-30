package com.xmut.osm.manager.rest.controller;

import com.xmut.osm.common.bean.PageBean;
import com.xmut.osm.common.bean.PageInfo;
import com.xmut.osm.common.bean.ResultVO;
import com.xmut.osm.entity.Brand;
import com.xmut.osm.goods.feign.BrandServiceClient;
import com.xmut.osm.manager.rest.util.ControllerTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 阮胜
 * @date 2018/7/22 23:34
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@RestController
@RequestMapping("/brand")
@Slf4j
public class BrandController {
    private final ControllerTemplate<Brand> controllerTemplate;

    public BrandController(BrandServiceClient brandServiceClient) {
        this.controllerTemplate = ControllerTemplate.getInstance(brandServiceClient);
    }

    @GetMapping("/all")
    public PageInfo<Brand> fetchBrandAll(PageBean pageBean) {
        return controllerTemplate.fetchAll(pageBean);
    }

    @PostMapping("/save")
    public ResultVO save(@RequestBody Brand brand, BindingResult bindingResult) {
        if (brand.getId() == null) {
            brand.setId(0);
        }
        return controllerTemplate.save(brand, bindingResult);
    }

    @DeleteMapping("/deleteAll")
    public ResultVO<List<Integer>> deleteAll(Integer[] ids) {
        return controllerTemplate.deleteAll(ids);
    }
}
