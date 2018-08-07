package com.xmut.osm.test.user;

import com.xmut.osm.common.enumeration.PermissionProjectEnum;
import com.xmut.osm.common.enumeration.RoleEnum;
import com.xmut.osm.entity.Permission;
import com.xmut.osm.entity.Role;
import com.xmut.osm.entity.User;
import com.xmut.osm.repository.PermissionRepository;
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
    public static final String ROLE_CANNOT_FOUND = "找不到指定权限";
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PermissionRepository permissionRepository;

    @Test
    public void insertUser() {
        Role roleAdmin = roleRepository.save(new Role(RoleEnum.ADMIN.getCode(), RoleEnum.ADMIN.getName()));
        Role roleUser = roleRepository.save(new Role(RoleEnum.USER.getCode(), RoleEnum.USER.getName()));
        Role roleSeller = roleRepository.save(new Role(RoleEnum.SELLER.getCode(), RoleEnum.SELLER.getName()));

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

    @Test
    public void insertPermission() {
        Role roleAdmin = roleRepository.findByCode(RoleEnum.ADMIN.getCode()).orElseThrow(() -> new RuntimeException(ROLE_CANNOT_FOUND));
        Role roleUser = roleRepository.findByCode(RoleEnum.USER.getCode()).orElseThrow(() -> new RuntimeException(ROLE_CANNOT_FOUND));
        Role roleSeller = roleRepository.findByCode(RoleEnum.SELLER.getCode()).orElseThrow(() -> new RuntimeException(ROLE_CANNOT_FOUND));


        //resource
        permissionRepository.save(new Permission("/css/**", PermissionProjectEnum.RESOURCE.getProjectCode(), PermissionProjectEnum.RESOURCE.getProject(), null));
        permissionRepository.save(new Permission("/img/**", PermissionProjectEnum.RESOURCE.getProjectCode(), PermissionProjectEnum.RESOURCE.getProject(), null));
        permissionRepository.save(new Permission("/js/**", PermissionProjectEnum.RESOURCE.getProjectCode(), PermissionProjectEnum.RESOURCE.getProject(), null));
        permissionRepository.save(new Permission("/plugins/**", PermissionProjectEnum.RESOURCE.getProjectCode(), PermissionProjectEnum.RESOURCE.getProject(), null));

        //manager
        permissionRepository.save(new Permission("/login.html", PermissionProjectEnum.MANAGER.getProjectCode(),
                PermissionProjectEnum.MANAGER.getProject(), null));
        permissionRepository.save(new Permission("/**", PermissionProjectEnum.MANAGER.getProjectCode(),
                PermissionProjectEnum.MANAGER.getProject(), roleAdmin));

        //seller
        permissionRepository.save(new Permission("/login.html", PermissionProjectEnum.SELLER.getProjectCode(),
                PermissionProjectEnum.SELLER.getProject(), null));
        permissionRepository.save(new Permission("/**", PermissionProjectEnum.SELLER.getProjectCode(),
                PermissionProjectEnum.SELLER.getProject(), roleSeller));


    }
}
