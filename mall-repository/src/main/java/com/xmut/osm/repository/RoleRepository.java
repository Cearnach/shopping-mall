package com.xmut.osm.repository;

import com.xmut.osm.entity.Role;
import com.xmut.osm.repository.base.BaseRepository;

import java.util.Optional;

/**
 * @author 阮胜
 * @date 2018/8/4 18:29
 */
public interface RoleRepository extends BaseRepository<Role, Integer> {

    Optional<Role> findByName(String roleName);

    Optional<Role> findByCode(Integer roleCode);
}
