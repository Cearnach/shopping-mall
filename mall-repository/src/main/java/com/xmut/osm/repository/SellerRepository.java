package com.xmut.osm.repository;

import com.xmut.osm.entity.Seller;
import com.xmut.osm.repository.base.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * @author 阮胜
 * @date 2018/8/6 14:41
 */
public interface SellerRepository extends BaseRepository<Seller, Integer> {

    Optional<Seller> findByAccount(String account);

    Page<Seller> findByCompanyName(String companyName, Pageable pageBean);

    Page<Seller> findByStoreName(String storeName , Pageable pageBean);

    Page<Seller> findByCompanyNameOrStoreName(String companyName, String storeName , Pageable pageBean);

    Page<Seller> findAllByStatus(Integer status, Pageable pageable);
/*
    @Modifying
    @Query("update Seller set Seller.status = :statusCode where Seller.id = :sellerId")
    void updateStatusCode(@Param("sellerId") Integer sellerId, @Param("statusCode") Integer statusCode);*/
}
