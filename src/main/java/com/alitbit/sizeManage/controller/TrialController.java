package com.alitbit.sizeManage.controller;

import com.alitbit.sizeManage.bean.Ranges;
import com.alitbit.sizeManage.bean.Trial;
import com.alitbit.sizeManage.exception.Exceptions;
import com.alitbit.sizeManage.service.TrialService;
import com.alitbit.sizeManage.util.ResultVOUtil;
import com.alitbit.sizeManage.vo.ResultVO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trial")
public class TrialController {

    @Autowired
    private TrialService trialService;

    @GetMapping("/list")
    public ResultVO list(){
        return ResultVOUtil.success(trialService.findList());
    }

    @GetMapping("/ableByAsc")
    public ResultVO able(){
        return ResultVOUtil.success(trialService.findEnableTrialsOrderByASC());
    }

    @PostMapping("/updateAbleTrial")
    public ResultVO updateAbleTrial(
            @RequestParam String ableTrialStr
    ){
        List<Trial> trials;
        try {
            trials = new Gson().fromJson(ableTrialStr,
                    new TypeToken<List<Trial>>() {
                    }.getType());
        } catch (Exception e) {
            throw new Exceptions("保存设置失败，参数错误");
        }
        return ResultVOUtil.success(trialService.updateAbleTrial(trials));
    }

}
