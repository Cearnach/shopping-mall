package com.xmut.osm.repository;

import com.xmut.osm.entity.Specification;
import com.xmut.osm.repository.base.BaseRepository;

import java.util.Optional;

/**
 * @author 阮胜
 * @date 2018/7/30 14:23
 */
public interface SpecificationRepository extends BaseRepository<Specification, Integer> {
    Optional<Specification> findByName(String name);
}
