package com.xmut.osm.security.provider;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

/**
 * {@link AuthorizeConfigProvider} 统一管理接口
 *
 * @author 阮胜
 * @date 2018/8/4 14:57
 */
public interface AuthorizeConfigProviderManager {
    /**
     * 配置信息
     *
     * @param config
     */
    void configure(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config);
}
