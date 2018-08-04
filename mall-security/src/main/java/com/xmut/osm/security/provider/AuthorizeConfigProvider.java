package com.xmut.osm.security.provider;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

/**
 * Url 权限配置统一实现该接口
 *
 *
 * @author 阮胜
 * @date 2018/8/4 14:57
 */
public interface AuthorizeConfigProvider {
    /**
     * 配置信息
     *
     * @param config
     */
    void configure(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config);
}
