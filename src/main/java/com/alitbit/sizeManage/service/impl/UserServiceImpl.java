package com.alitbit.sizeManage.service.impl;

import com.alitbit.sizeManage.bean.User;
import com.alitbit.sizeManage.dao.UserDao;
import com.alitbit.sizeManage.exception.Exceptions;
import com.alitbit.sizeManage.service.UserService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> users = userDao.findByUsername(username);

        if (CollectionUtils.isEmpty(users)){
            throw new UsernameNotFoundException("用户 "+username+" 不存在");
        }

        return users.get(0);
    }

    @Override
    public User getCurrentUser() {
         User currentUser = (User) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
         try {
             currentUser  = userDao.findById(currentUser.getId()).get();
         }catch (NoSuchElementException e){
         }
         return currentUser;
    }

    @Override
    public User updatePwd(String oldPwd, String pwd) {
        User currentUser = this.getCurrentUser();

        if (!new BCryptPasswordEncoder().matches(oldPwd, currentUser.getPassword())){
            throw new Exceptions("原密码错误");
        }

        currentUser.setPassword(new BCryptPasswordEncoder().encode(pwd));

        return  userDao.save(currentUser);
    }
}
