package com.xmut.osm.manager.service;

import com.xmut.osm.common.bean.PageBean;
import com.xmut.osm.common.enumeration.SellerStatusEnum;
import com.xmut.osm.entity.Seller;
import com.xmut.osm.service.base.BaseService;
import org.springframework.data.domain.Page;

/**
 * @author 阮胜
 * @date 2018/8/6 20:47
 */
public interface SellerService extends BaseService<Seller, Integer> {
    Page<Seller> findByStatus(SellerStatusEnum sellerStatusEnum, PageBean pageBean);

    void updateStatusCode(Integer sellerId, Integer statusCode);

    Page<Seller> findByCompanyName(String companyName, PageBean pageBean);

    Page<Seller> findByStoreName(String storeName, PageBean pageBean);

    Page<Seller> findByCompanyNameOrStoreName(String companyName, String storeName, PageBean pageBean);
}
