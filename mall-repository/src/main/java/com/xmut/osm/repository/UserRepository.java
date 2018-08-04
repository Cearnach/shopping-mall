package com.xmut.osm.repository;

import com.xmut.osm.entity.User;
import com.xmut.osm.repository.base.BaseRepository;

import java.util.Optional;

/**
 * @author 阮胜
 * @date 2018/8/4 16:40
 */
public interface UserRepository extends BaseRepository<User, Long> {
    Optional<User> findByAccount(String account);
    Optional<User> findByAccountAndPassword(String account, String password);
}
