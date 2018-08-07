package com.xmut.osm.repository;

import com.xmut.osm.entity.Permission;
import com.xmut.osm.repository.base.BaseRepository;

import java.util.List;

/**
 * @author 阮胜
 * @date 2018/8/7 14:38
 */
public interface PermissionRepository extends BaseRepository<Permission, Integer> {
    List<Permission> findAllByProjectCode(Integer projectCode);
}
