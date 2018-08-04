package com.xmut.osm.zuul.provider;

import com.xmut.osm.enumeration.RoleEnum;
import com.xmut.osm.security.provider.AuthorizeConfigProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * @author 阮胜
 * @date 2018/8/4 15:18
 */
@Component
public class UserAuthorizeConfigProvider implements AuthorizeConfigProvider {
    @Override
    public void configure(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        config.antMatchers("/user/info").hasRole(RoleEnum.USER.getName());
    }
}
