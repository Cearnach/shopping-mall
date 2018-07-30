package com.xmut.osm.repository;

import com.xmut.osm.entity.SpecificationOption;
import com.xmut.osm.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author 阮胜
 * @date 2018/7/30 14:24
 */
public interface SpecificationOptionRepository extends BaseRepository<SpecificationOption, Integer> {
    List<SpecificationOption> findBySpecificationId(Integer id);

    @Query("select id from SpecificationOption where specification.id = :specId and id not in (:optionIds)")
    List<Integer> findBySpecificationIdAndIdNotIn(@Param("specId") Integer specId, @Param("optionIds") List<Integer> optionIdList);

    @Modifying
    @Query("delete from SpecificationOption where id in (:idList)")
    void deleteAll(@Param("idList") List<Integer> idList);
}
