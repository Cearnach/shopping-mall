package com.xmut.osm.goods.controller;

import com.xmut.osm.common.bean.PageBean;
import com.xmut.osm.common.bean.PageInfo;
import com.xmut.osm.entity.ItemCategory;
import com.xmut.osm.goods.service.ItemCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 阮胜
 * @date 2018/8/15 21:45
 */
@RestController
@RequestMapping("/itemCat")
@Slf4j
public class ItemCategoryController {
    private final ItemCategoryService itemCategoryService;

    public ItemCategoryController(ItemCategoryService itemCategoryService) {
        this.itemCategoryService = itemCategoryService;
    }

    @GetMapping("/all")
    public PageInfo<ItemCategory> fetchItemCategoryAll(PageBean pageBean) {
        Page<ItemCategory> itemCategoryPage = itemCategoryService.findAll(pageBean);
        return new PageInfo<>(pageBean.getPage(), itemCategoryPage.getTotalElements(), itemCategoryPage.getContent());
    }

    @PostMapping("/save")
    public boolean save(@RequestBody ItemCategory itemCategory) {
        itemCategoryService.save(itemCategory);
        return true;
    }

    @DeleteMapping("/deleteAll")
    public List<Integer> deleteAll(Integer[] ids) {
        return itemCategoryService.deleteIn(ids);
    }
}
