package com.xmut.osm.security.provider.impl;

import com.xmut.osm.common.enumeration.PermissionProjectEnum;
import com.xmut.osm.entity.Permission;
import com.xmut.osm.repository.PermissionRepository;
import com.xmut.osm.security.provider.AuthorizeConfigProvider;
import com.xmut.osm.security.util.PermissionUtil;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 阮胜
 * @date 2018/8/6 20:01
 */
@Component
public class ResourceAuthorizeConfigProvider implements AuthorizeConfigProvider {
    private final PermissionRepository permissionRepository;

    public ResourceAuthorizeConfigProvider(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    public void configure(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        List<Permission> permissionList = permissionRepository.findAllByProjectCode(PermissionProjectEnum.RESOURCE.getProjectCode());
        PermissionUtil.applyPermission(config, permissionList);
    }
}
