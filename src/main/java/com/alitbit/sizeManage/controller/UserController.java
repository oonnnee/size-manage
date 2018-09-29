package com.alitbit.sizeManage.controller;

import com.alitbit.sizeManage.service.UserService;
import com.alitbit.sizeManage.util.ResultVOUtil;
import com.alitbit.sizeManage.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/updatePwd")
    public ResultVO updatePwd(
            @RequestParam String oldPwd,
            @RequestParam String pwd
    ){
        return ResultVOUtil.success(userService.updatePwd(oldPwd, pwd));
    }

}
