package com.alitbit.sizeManage.dao;

import com.alitbit.sizeManage.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDao extends JpaRepository<User, Integer> {

    List<User> findByUsername(String username);
}
