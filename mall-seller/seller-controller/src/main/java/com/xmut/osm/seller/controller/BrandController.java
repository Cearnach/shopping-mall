package com.xmut.osm.seller.controller;

import com.xmut.osm.common.bean.PageBean;
import com.xmut.osm.common.bean.PageInfo;
import com.xmut.osm.common.util.ControllerTemplate;
import com.xmut.osm.entity.Brand;
import com.xmut.osm.goods.feign.BrandServiceClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 阮胜
 * @date 2018/8/23 14:20
 */
@RestController
@RequestMapping("/brand")
public class BrandController {
    private final ControllerTemplate<Brand> controllerTemplate;

    public BrandController(BrandServiceClient brandServiceClient) {
        this.controllerTemplate = ControllerTemplate.getInstance(brandServiceClient);
    }
    @GetMapping("/all")
    public PageInfo<Brand> fetchAll(PageBean pageBean) {
        return controllerTemplate.fetchAll(pageBean);
    }

}
