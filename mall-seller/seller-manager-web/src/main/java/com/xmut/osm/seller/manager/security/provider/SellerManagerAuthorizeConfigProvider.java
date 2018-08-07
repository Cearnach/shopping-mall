package com.xmut.osm.seller.manager.security.provider;

import com.xmut.osm.common.enumeration.PermissionProjectEnum;
import com.xmut.osm.common.enumeration.RoleEnum;
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
 * @date 2018/8/6 20:00
 */
@Component
public class SellerManagerAuthorizeConfigProvider implements AuthorizeConfigProvider {
    private final PermissionRepository permissionRepository;

    public SellerManagerAuthorizeConfigProvider(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    public void configure(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        config.antMatchers("/register.html", "/register").permitAll();
        config.antMatchers(("/all")).hasAnyRole(RoleEnum.ADMIN.getName(), RoleEnum.SELLER.getName());
        List<Permission> permissionList = permissionRepository.findAllByProjectCode(PermissionProjectEnum.MANAGER.getProjectCode());
        PermissionUtil.applyPermission(config, permissionList);
    }


}
