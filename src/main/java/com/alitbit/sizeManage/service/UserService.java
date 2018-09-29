package com.alitbit.sizeManage.service;

import com.alitbit.sizeManage.bean.User;

public interface UserService {

    User getCurrentUser();

    User updatePwd(String oldPwd, String pwd);

}
