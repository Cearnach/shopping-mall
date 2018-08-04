package com.xmut.osm.test.user;

import com.xmut.osm.entity.Role;
import com.xmut.osm.entity.User;
import com.xmut.osm.repository.RoleRepository;
import com.xmut.osm.repository.UserRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

/**
 * @author 阮胜
 * @date 2018/8/4 18:26
 */
public class UserRepositoryTest extends BaseTest {
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;


    @Test
    public void insertUser() {
        Role roleAdmin = roleRepository.save(new Role(100, "超级管理员"));
        Role roleUser = roleRepository.save(new Role(200, "普通用户"));

        User user = new User();
        user.setAccount("jack");
        user.setPassword("jackpwd");
        user.setRoles(Arrays.asList(roleAdmin, roleUser));
        userRepository.save(user);

        User user2 = new User();
        user2.setAccount("rose");
        user2.setPassword("rosepwd");
        user2.setRoles(Arrays.asList(roleUser));
        userRepository.save(user2);
    }
}
