package com.xmut.osm.seller.controller;

import com.xmut.osm.common.bean.PageBean;
import com.xmut.osm.common.bean.PageInfo;
import com.xmut.osm.common.util.ControllerTemplate;
import com.xmut.osm.entity.ItemCategory;
import com.xmut.osm.goods.feign.ItemCategoryServiceClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 阮胜
 * @date 2018/8/23 14:20
 */
@RestController
@RequestMapping("/itemCat")
public class ItemCategoryController {
    private final ControllerTemplate<ItemCategory> controllerTemplate;

    public ItemCategoryController(ItemCategoryServiceClient itemCategoryServiceClient) {
        this.controllerTemplate = ControllerTemplate.getInstance(itemCategoryServiceClient);
    }
    @GetMapping("/all")
    public PageInfo<ItemCategory> fetchAll(PageBean pageBean) {
        return controllerTemplate.fetchAll(pageBean);
    }

}
