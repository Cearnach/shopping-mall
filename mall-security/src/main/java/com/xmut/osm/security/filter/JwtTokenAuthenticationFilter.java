package com.xmut.osm.security.filter;

import com.xmut.osm.security.property.JwtAuthenticationProperties;
import com.xmut.osm.security.util.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 阮胜
 * @date 2018/8/4 14:28
 */
@Slf4j
public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {

    /**
     * 用户权限列表
     */
    private static final String AUTHORITIES = "authorities";
    private final JwtAuthenticationProperties jwtAuthenticationProperties;

    public JwtTokenAuthenticationFilter(JwtAuthenticationProperties jwtAuthenticationProperties) {
        this.jwtAuthenticationProperties = jwtAuthenticationProperties;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String tokenHeader = request.getHeader(jwtAuthenticationProperties.getHeader());
        if (!StringUtils.isEmpty(tokenHeader) && tokenHeader.startsWith(jwtAuthenticationProperties.getPrefix())) {
            String jwtToken = tokenHeader.replace(jwtAuthenticationProperties.getPrefix(), "");
            try {
                Claims claims = JwtTokenUtil.parse(jwtToken, jwtAuthenticationProperties.getSecret());
                System.out.println(claims);
                String username = claims.getSubject();
                @SuppressWarnings("unchecked")
                List<String> authorities = claims.get(AUTHORITIES, List.class);
                if (!StringUtils.isEmpty(username) && !CollectionUtils.isEmpty(authorities)) {
                    List<SimpleGrantedAuthority> grantedAuthorityList = authorities.stream()
                            .map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toList());
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username,
                            null, grantedAuthorityList);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (Exception e) {
                log.error(e.getMessage());
                SecurityContextHolder.clearContext();
            }
        }
        filterChain.doFilter(request, response);
    }
}
