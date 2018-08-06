package com.xmut.osm.seller.manager.security.provider;

import com.xmut.osm.security.provider.AuthorizeConfigProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * @author 阮胜
 * @date 2018/8/6 20:00
 */
@Component
public class SellerManagerAuthorizeConfigProvider implements AuthorizeConfigProvider {
    @Override
    public void configure(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        config.antMatchers("/seller/**").permitAll();
    }
}
