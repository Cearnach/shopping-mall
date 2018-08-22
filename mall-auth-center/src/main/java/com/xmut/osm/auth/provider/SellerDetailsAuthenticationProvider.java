package com.xmut.osm.auth.provider;

import com.xmut.osm.auth.token.SellerUsernamePasswordAuthenticationToken;

/**
 * 商家登录token
 *
 * @author 阮胜
 * @date 2018/8/6 16:19
 */
public class SellerDetailsAuthenticationProvider extends UserDetailsAuthenticationProvider {

    private static final String AUTH_SELLER_USERNAME_PASSWORD_TOKEN_CLASS_NAME = SellerUsernamePasswordAuthenticationToken.class.getSimpleName();

    /**
     * 通过该方法来决定是否使用该provider
     *
     * @param authentication UsernameAndPasswordAuthenticationFilter 过滤器传过来的Token类型
     * @return true为使用该provider.反之亦然.
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return AUTH_SELLER_USERNAME_PASSWORD_TOKEN_CLASS_NAME.equals(authentication.getSimpleName());
    }
}
