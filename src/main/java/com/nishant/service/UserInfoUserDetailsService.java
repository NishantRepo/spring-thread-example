package com.nishant.service;

import com.nishant.config.UserInfoUserDetails;
import com.nishant.entity.UserInfo;
import com.nishant.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {

    @Autowired
    private UserInfoRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userInfo = repository.findByUsername(username);

        return userInfo.map(UserInfoUserDetails::new)
                .orElseThrow(() ->new UsernameNotFoundException("user not found: " + username));
    }
}
