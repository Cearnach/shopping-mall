package com.xmut.osm.auth.config;

import com.xmut.osm.auth.filter.JwtUserAuthenticationFilter;
import com.xmut.osm.security.property.JwtAuthenticationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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

    public SecurityConfig(JwtAuthenticationProperties jwtAuthenticationProperties, UserDetailsService userDetailsService) {
        this.jwtAuthenticationProperties = jwtAuthenticationProperties;
        this.userDetailsService = userDetailsService;
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
                .addFilterBefore(new JwtUserAuthenticationFilter(jwtAuthenticationProperties, authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers(jwtAuthenticationProperties.getLoginUrl()).permitAll()
                .anyRequest().authenticated();
    }
}
