package com.xmut.osm.security.util;

import com.xmut.osm.entity.Permission;
import com.xmut.osm.entity.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 阮胜
 * @date 2018/8/7 15:40
 */
@Slf4j
public class PermissionUtil {

    private static final String APPLY_PERMISSION_URL_ROLE = "Apply permission , url:{} , Role:{}";
    private static final String ROLE_ANYONE = "Anyone";

    public static void applyPermission(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config, List<Permission> permissionList) {
        permissionList.forEach(permission -> {
            String url = permission.getUrl();
            List<Role> roles = permission.getRoles();
            if (CollectionUtils.isEmpty(roles)) {
                log.info(APPLY_PERMISSION_URL_ROLE, url, ROLE_ANYONE);
                config.antMatchers(url).permitAll();
            } else {
                String[] roleArr = roles.stream().map(Role::getName).toArray(String[]::new);
                log.info(APPLY_PERMISSION_URL_ROLE, url, Arrays.toString(roleArr));
                config.antMatchers(url).hasAnyRole(roleArr);
            }
        });
    }

    public static void main(String[] args) {

    }
}
