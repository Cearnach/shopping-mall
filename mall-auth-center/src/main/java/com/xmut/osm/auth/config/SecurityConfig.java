package com.xmut.osm.auth.config;

import com.xmut.osm.auth.filter.JwtUserUsernameAndPasswordAuthenticationFilter;
import com.xmut.osm.auth.handler.JwtAuthenticationFailureHandler;
import com.xmut.osm.auth.handler.JwtAuthenticationSuccessHandler;
import com.xmut.osm.auth.provider.SellerDetailsAuthenticationProvider;
import com.xmut.osm.auth.provider.UserDetailsAuthenticationProvider;
import com.xmut.osm.security.property.JwtAuthenticationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;

    @Autowired
    @Qualifier("userDetailService")
    private UserDetailsService userDetailsService;

    @Autowired
    @Qualifier("sellerDetailService")
    private UserDetailsService sellerDetailService;


    public SecurityConfig(JwtAuthenticationProperties jwtAuthenticationProperties, PasswordEncoder passwordEncoder) {
        this.jwtAuthenticationProperties = jwtAuthenticationProperties;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        UserDetailsAuthenticationProvider userDetailsAuthenticationProvider = new UserDetailsAuthenticationProvider();
        userDetailsAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        userDetailsAuthenticationProvider.setUserDetailsService(userDetailsService);

        SellerDetailsAuthenticationProvider sellerDetailsAuthenticationProvider = new SellerDetailsAuthenticationProvider();
        sellerDetailsAuthenticationProvider.setUserDetailsService(sellerDetailService);
        sellerDetailsAuthenticationProvider.setPasswordEncoder(passwordEncoder);

        auth.authenticationProvider(userDetailsAuthenticationProvider);
        auth.authenticationProvider(sellerDetailsAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .addFilterBefore(jwtUserAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        httpSecurity
                .addFilterBefore(jwtSellerAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
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
                .authorizeRequests()
                .antMatchers(jwtAuthenticationProperties.getUserLoginUrl(), jwtAuthenticationProperties.getSellerLoginUrl()).permitAll()
                .antMatchers(jwtAuthenticationProperties.getUserLoginUrl()).permitAll()
                .anyRequest().authenticated();
    }

    @Bean("jwtUserUsernameAndPasswordAuthenticationFilter")
    JwtUserUsernameAndPasswordAuthenticationFilter jwtUserAuthenticationFilter() throws Exception {
        return new JwtUserUsernameAndPasswordAuthenticationFilter(
                jwtAuthenticationProperties.getUserLoginUrl(),
                jwtAuthenticationProperties, authenticationManager()
        );
    }

    @Bean("jwtSellerUsernameAndPasswordAuthenticationFilter")
    JwtUserUsernameAndPasswordAuthenticationFilter jwtSellerAuthenticationFilter() throws Exception {
        return new JwtUserUsernameAndPasswordAuthenticationFilter(
                jwtAuthenticationProperties.getSellerLoginUrl(),
                jwtAuthenticationProperties, authenticationManager()
        );
    }
}
