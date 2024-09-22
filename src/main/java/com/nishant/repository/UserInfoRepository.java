package com.nishant.repository;

import com.nishant.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {


    Optional<UserInfo> findByUsername(String username);
}
