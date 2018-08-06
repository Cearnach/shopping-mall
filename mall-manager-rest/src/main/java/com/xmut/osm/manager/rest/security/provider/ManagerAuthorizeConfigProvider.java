package com.xmut.osm.manager.rest.security.provider;

import com.xmut.osm.common.enumeration.RoleEnum;
import com.xmut.osm.security.provider.AuthorizeConfigProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * @author 阮胜
 * @date 2018/8/4 22:31
 */
@Component
public class ManagerAuthorizeConfigProvider implements AuthorizeConfigProvider {
    @Override
    public void configure(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        config.antMatchers(
                "/css/**",
                "/img/**",
                "/js/**",
                "/plugins/**",
                "/login.html").permitAll()
                .antMatchers("/**").hasRole(RoleEnum.ADMIN.getName());
    }
}