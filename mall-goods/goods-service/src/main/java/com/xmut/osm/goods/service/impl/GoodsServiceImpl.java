package com.xmut.osm.goods.service.impl;

import com.xmut.osm.common.enumeration.GoodsStatusEnum;
import com.xmut.osm.dto.GoodsDTO;
import com.xmut.osm.entity.Brand;
import com.xmut.osm.entity.Goods;
import com.xmut.osm.entity.ItemCategory;
import com.xmut.osm.entity.Seller;
import com.xmut.osm.exception.TargetEntityNotFound;
import com.xmut.osm.goods.service.GoodsService;
import com.xmut.osm.repository.BrandRepository;
import com.xmut.osm.repository.GoodsRepository;
import com.xmut.osm.repository.ItemCategoryRepository;
import com.xmut.osm.repository.SellerRepository;
import com.xmut.osm.service.base.BaseServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * @author 阮胜
 * @date 2018/7/21 14:52
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class GoodsServiceImpl extends BaseServiceImpl<Goods, String, GoodsRepository> implements GoodsService {
    private final BrandRepository brandRepository;
    private final ItemCategoryRepository itemCategoryRepository;
    private final SellerRepository sellerRepository;
    public GoodsServiceImpl(BrandRepository brandRepository, ItemCategoryRepository itemCategoryRepository, SellerRepository sellerRepository) {
        this.brandRepository = brandRepository;
        this.itemCategoryRepository = itemCategoryRepository;
        this.sellerRepository = sellerRepository;
    }

    @Override
    public Goods save(GoodsDTO goodsDTO) throws TargetEntityNotFound {
        String goodsId = goodsDTO.getId();
        Goods goods;
        if (StringUtils.isEmpty(goodsId)) {
            goods = new Goods();
        } else {
            goods = repository.findById(goodsId).orElseThrow(TargetEntityNotFound::new);
        }
        BeanUtils.copyProperties(goodsDTO,goods);
        Brand brand = brandRepository.findById(goodsDTO.getBrandId())
                .orElseThrow(TargetEntityNotFound::new);
        ItemCategory itemCategory = itemCategoryRepository.findById(goodsDTO.getItemCategoryId())
                .orElseThrow(TargetEntityNotFound::new);
        Seller seller = sellerRepository.findByAccount(goodsDTO.getSellerAccount())
                .orElseThrow(TargetEntityNotFound::new);
        goods.setBrand(brand);
        goods.setItemCategory(itemCategory);
        goods.setSeller(seller);
        goods.setStatus(GoodsStatusEnum.SOLD_ON.getCode());
        goods.setUpdateDate(new Date());
        return repository.save(goods);
    }
}
