package com.xmut.osm.security.util;

import com.xmut.osm.entity.Permission;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.util.StringUtils;

import java.util.List;

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
            if (permission.getRole() == null || StringUtils.isEmpty(permission.getRole().getName())) {
                log.info(APPLY_PERMISSION_URL_ROLE, url, ROLE_ANYONE);
                config.antMatchers(url).permitAll();
            } else {
                String role = permission.getRole().getName();
                log.info(APPLY_PERMISSION_URL_ROLE, url, role);
                config.antMatchers(url, role);
            }
        });
    }
}
