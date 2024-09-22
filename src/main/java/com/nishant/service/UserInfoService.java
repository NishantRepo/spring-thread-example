package com.nishant.service;

import com.nishant.entity.UserInfo;
import com.nishant.repository.UserInfoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserInfoService {

    private final UserInfoRepository repository;

    private final PasswordEncoder encoder;

    @Transactional
    public String addUserInfo(UserInfo userInfo) {
        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
        UserInfo info = repository.save(userInfo);

        return "User Added";
    }

    public List<UserInfo> gerAllUserInfo() {
        return repository.findAll();
    }
}
