package com.xmut.osm.zuul.config;

import com.xmut.osm.security.property.JwtAuthenticationProperties;
import com.xmut.osm.security.provider.AuthorizeConfigProviderManager;
import com.xmut.osm.zuul.filter.JwtTokenAuthenticationFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

/**
 * @author 阮胜
 * @date 2018/8/4 14:51
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtAuthenticationProperties jwtAuthenticationProperties;
    private final AuthorizeConfigProviderManager authorizeConfigProviderManager;
    public SecurityConfig(JwtAuthenticationProperties jwtAuthenticationProperties, AuthorizeConfigProviderManager authorizeConfigProviderManager) {
        this.jwtAuthenticationProperties = jwtAuthenticationProperties;
        this.authorizeConfigProviderManager = authorizeConfigProviderManager;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //手动实现的权限验证管理器
        authorizeConfigProviderManager.configure(http.authorizeRequests());

        http
                .csrf().disable()
                .logout().disable()
                .formLogin().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .anonymous()
                .and()
                .exceptionHandling().authenticationEntryPoint(
                (req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .and()
                .addFilterAfter(new JwtTokenAuthenticationFilter(jwtAuthenticationProperties),
                        UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers(jwtAuthenticationProperties.getLoginUrl()).permitAll();
    }
}
