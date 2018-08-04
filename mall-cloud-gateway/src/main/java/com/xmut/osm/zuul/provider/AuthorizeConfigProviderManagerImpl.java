package com.xmut.osm.zuul.provider;

import com.xmut.osm.security.provider.AuthorizeConfigProvider;
import com.xmut.osm.security.provider.AuthorizeConfigProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author 阮胜
 * @date 2018/8/4 15:00
 */
@Component
public class AuthorizeConfigProviderManagerImpl implements AuthorizeConfigProviderManager {
    /**
     * 如果项目没有该接口的实现类,则启动扫描会报错.需要去掉@Component注解,并且注释掉SecurityConfig类里面当前类的引用.
     */
    private final Set<AuthorizeConfigProvider> authorizeConfigProviderManagers;

    public AuthorizeConfigProviderManagerImpl(Set<AuthorizeConfigProvider> authorizeConfigProviderManagers) {
        this.authorizeConfigProviderManagers = authorizeConfigProviderManagers;
    }

    /**
     * 加载并配置所有 {@link AuthorizeConfigProviderManager} 接口的实现类
     *
     * @param config
     */
    @Override
    public void configure(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        for (AuthorizeConfigProvider authorizeConfigProvider : authorizeConfigProviderManagers) {
            authorizeConfigProvider.configure(config);
        }
    }

}
