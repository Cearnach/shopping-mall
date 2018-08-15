package com.xmut.osm.goods.service.impl;

import com.xmut.osm.entity.ItemCategory;
import com.xmut.osm.goods.service.ItemCategoryService;
import com.xmut.osm.repository.ItemCategoryRepository;
import com.xmut.osm.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 阮胜
 * @date 2018/8/15 21:46
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ItemCategoryServiceImpl extends BaseServiceImpl<ItemCategory, Integer, ItemCategoryRepository>
        implements ItemCategoryService {
}
