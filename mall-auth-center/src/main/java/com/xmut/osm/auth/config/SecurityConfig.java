package com.xmut.osm.auth.config;

import com.xmut.osm.auth.filter.JwtUserAuthenticationFilter;
import com.xmut.osm.auth.handler.JwtAuthenticationFailureHandler;
import com.xmut.osm.auth.handler.JwtAuthenticationSuccessHandler;
import com.xmut.osm.security.property.JwtAuthenticationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

/**
 * @author 阮胜
 * @date 2018/8/4 15:53
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtAuthenticationProperties jwtAuthenticationProperties;
    private final UserDetailsService userDetailsService;
    private final JwtAuthenticationSuccessHandler jwtAuthenticationSuccessHandler;
    private final JwtAuthenticationFailureHandler jwtAuthenticationFailureHandler;
    public SecurityConfig(JwtAuthenticationProperties jwtAuthenticationProperties, UserDetailsService userDetailsService, JwtAuthenticationSuccessHandler jwtAuthenticationSuccessHandler, JwtAuthenticationFailureHandler jwtAuthenticationFailureHandler) {
        this.jwtAuthenticationProperties = jwtAuthenticationProperties;
        this.userDetailsService = userDetailsService;
        this.jwtAuthenticationSuccessHandler = jwtAuthenticationSuccessHandler;
        this.jwtAuthenticationFailureHandler = jwtAuthenticationFailureHandler;
    }


    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
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
                .userDetailsService(userDetailsService)
                .addFilterBefore(jwtUserAuthenticationFilter(),
                        UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers(jwtAuthenticationProperties.getLoginUrl()).permitAll()
                .anyRequest().authenticated();
    }

    @Bean
    JwtUserAuthenticationFilter jwtUserAuthenticationFilter() throws Exception {
        JwtUserAuthenticationFilter jwtUserAuthenticationFilter = new JwtUserAuthenticationFilter(jwtAuthenticationProperties, authenticationManager());
        jwtUserAuthenticationFilter.setAuthenticationSuccessHandler(jwtAuthenticationSuccessHandler);
        jwtUserAuthenticationFilter.setAuthenticationFailureHandler(jwtAuthenticationFailureHandler);
        return jwtUserAuthenticationFilter;
    }

}
