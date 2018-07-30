package com.xmut.osm.repository;

import com.xmut.osm.entity.SpecificationOption;
import com.xmut.osm.repository.base.BaseRepository;

import java.util.List;

/**
 * @author 阮胜
 * @date 2018/7/30 14:24
 */
public interface SpecificationOptionRepository extends BaseRepository<SpecificationOption, Integer> {
    List<SpecificationOption> findBySpecificationId(Integer id);
}
