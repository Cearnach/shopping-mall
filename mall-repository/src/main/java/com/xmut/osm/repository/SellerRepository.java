package com.xmut.osm.repository;

import com.xmut.osm.entity.Seller;
import com.xmut.osm.repository.base.BaseRepository;
import org.springframework.data.domain.Page;

import java.util.Optional;

/**
 * @author 阮胜
 * @date 2018/8/6 14:41
 */
public interface SellerRepository extends BaseRepository<Seller, Integer> {
    Optional<Seller> findByAccount(String account);

    Page<Seller> findAllByStatus(Integer status);
}
