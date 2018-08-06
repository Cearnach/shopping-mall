package com.xmut.osm.auth.provider;

/**
 * 商家登录token
 * @author 阮胜
 * @date 2018/8/6 16:19
 */
public class SellerDetailsAuthenticationProvider extends UserDetailsAuthenticationProvider {

    private static final String AUTH_SELLER_USERNAME_PASSWORD_TOKEN_CLASS_NAME = SellerDetailsAuthenticationProvider.class.getSimpleName();

    /**
     * 通过该方法来决定是否使用该provider,true为使用该provider.反之亦然.
     * @param authentication
     * @return
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return AUTH_SELLER_USERNAME_PASSWORD_TOKEN_CLASS_NAME.equals(authentication.getSimpleName());
    }
}
