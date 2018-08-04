package com.xmut.osm.auth.service;

import com.xmut.osm.entity.User;
import com.xmut.osm.repository.UserRepository;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 阮胜
 * @date 2018/8/4 16:35
 */
@Component
public class UserDetailServiceImpl implements UserDetailsService {
    private static final String USER_NOT_EXIST = "用户不存在";
    public static final String ROLE_PREFIX = "ROLE_";
    private static final String ROLE_DELIMITED = ",";
    private final UserRepository userRepository;

    public UserDetailServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByAccount(username)
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_EXIST));
        List<String> roleNames = user.getRoles().stream()
                .map(role -> ROLE_PREFIX.concat(role.getName()))
                .collect(Collectors.toList());
        String roles = StringUtils.collectionToDelimitedString(roleNames, ROLE_DELIMITED);
        return new org.springframework.security.core.userdetails.User(user.getAccount(), user.getPassword()
                , AuthorityUtils.commaSeparatedStringToAuthorityList(roles));
    }
}
