package com.alitbit.sizeManage.controller;

import com.alitbit.sizeManage.service.SettingService;
import com.alitbit.sizeManage.util.ResultVOUtil;
import com.alitbit.sizeManage.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/setting")
public class SettingController {

    @Autowired
    private SettingService settingService;

    @GetMapping("/avg-size")
    public ResultVO avgSize(){
        return ResultVOUtil.success(settingService.isAvgOpen());
    }

    @PostMapping("/avg-size")
    public ResultVO avgSize(
            @RequestParam Boolean open
    ){
        settingService.updateAvg(open);
        return ResultVOUtil.success();
    }
}
