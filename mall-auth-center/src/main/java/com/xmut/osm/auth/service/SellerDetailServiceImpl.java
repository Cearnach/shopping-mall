package com.xmut.osm.auth.service;

import com.xmut.osm.entity.Seller;
import com.xmut.osm.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 阮胜
 * @date 2018/8/6 14:35
 */
@Component
@Qualifier("sellerDetailService")
public class SellerDetailServiceImpl implements UserDetailsService {
    private static final String ROLE_PREFIX = "ROLE_";
    private static final String ROLE_DELIMITED = ",";
    private static final String SELLER_NOT_FOUND = "找到不指定的商家";
    private final SellerRepository sellerRepository;

    public SellerDetailServiceImpl(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Seller seller = sellerRepository.findByAccount(username)
                .orElseThrow(() -> new UsernameNotFoundException(SELLER_NOT_FOUND));
        List<String> roleNames = seller.getRoles().stream()
                .map(role -> ROLE_PREFIX.concat(role.getName()))
                .collect(Collectors.toList());
        String roles = StringUtils.collectionToDelimitedString(roleNames, ROLE_DELIMITED);
        return new User(seller.getAccount(), seller.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList(roles));
    }
}
