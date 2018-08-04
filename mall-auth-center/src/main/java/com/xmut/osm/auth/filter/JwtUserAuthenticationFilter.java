package com.xmut.osm.auth.filter;

import com.xmut.osm.security.property.JwtAuthenticationProperties;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtUserAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final String POST = "POST";
    private final JwtAuthenticationProperties jwtAuthenticationProperties;

    public JwtUserAuthenticationFilter(JwtAuthenticationProperties jwtAuthenticationProperties) {
        this.jwtAuthenticationProperties = jwtAuthenticationProperties;
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(jwtAuthenticationProperties.getLoginUrl(), POST));
        this.setAuthenticationManager(getAuthenticationManager());
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals(POST)) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }

        String username = obtainUsername(request);
        String password = obtainPassword(request);

        if (username == null) {
            username = "";
        }

        if (password == null) {
            password = "";
        }

        username = username.trim();

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                username, password);
        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }
}
