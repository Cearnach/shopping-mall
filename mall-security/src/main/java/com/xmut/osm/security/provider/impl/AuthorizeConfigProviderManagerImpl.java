package com.xmut.osm.security.provider.impl;

import com.xmut.osm.security.provider.AuthorizeConfigProvider;
import com.xmut.osm.security.provider.AuthorizeConfigProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 阮胜
 * @date 2018/8/4 15:00
 */
@Component
public class AuthorizeConfigProviderManagerImpl implements AuthorizeConfigProviderManager {
    /**
     * 如果项目没有该接口的实现类,则启动扫描会报错.需要去掉@Component注解,并且注释掉SecurityConfig类里面当前类的引用.
     */
    private final Map<String, AuthorizeConfigProvider> authorizeConfigProviderManagerMap;

    public AuthorizeConfigProviderManagerImpl(Map<String, AuthorizeConfigProvider> authorizeConfigProviderManagerMap) {
        this.authorizeConfigProviderManagerMap = authorizeConfigProviderManagerMap;
    }


    /**
     * 加载并配置所有 {@link AuthorizeConfigProviderManager} 接口的实现类
     *
     * @param config
     */
    @Override
    public void configure(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        //静态资源优先配置.
        authorizeConfigProviderManagerMap.get("resourceAuthorizeConfigProvider").configure(config);
        for (AuthorizeConfigProvider configProvider : authorizeConfigProviderManagerMap.values()) {
            configProvider.configure(config);
        }
    }
}
