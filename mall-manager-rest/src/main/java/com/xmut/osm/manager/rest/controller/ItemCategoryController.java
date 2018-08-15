package com.xmut.osm.manager.rest.controller;

import com.xmut.osm.common.bean.PageBean;
import com.xmut.osm.common.bean.PageInfo;
import com.xmut.osm.common.bean.ResultVO;
import com.xmut.osm.common.util.ControllerTemplate;
import com.xmut.osm.entity.ItemCategory;
import com.xmut.osm.goods.feign.ItemCategoryServiceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 阮胜
 * @date 2018/8/15 21:42
 */
@RestController
@RequestMapping("/itemCat")
@Slf4j
public class ItemCategoryController {
    private final ControllerTemplate<ItemCategory> controllerTemplate;

    public ItemCategoryController(ItemCategoryServiceClient itemCategoryServiceClient) {
        this.controllerTemplate = ControllerTemplate.getInstance(itemCategoryServiceClient);
    }

    @GetMapping("/all")
    public PageInfo<ItemCategory> fetchAll(PageBean pageBean) {
        return controllerTemplate.fetchAll(pageBean);
    }

    @PostMapping("/save")
    public ResultVO save(@RequestBody ItemCategory itemCategory, BindingResult bindingResult) {
        if (itemCategory.getId() == null) {
            itemCategory.setId(0);
        }
        return controllerTemplate.save(itemCategory, bindingResult);
    }

    @DeleteMapping("/deleteAll")
    public ResultVO<List<Integer>> deleteAll(Integer[] ids) {
        return controllerTemplate.deleteAll(ids);
    }
}
