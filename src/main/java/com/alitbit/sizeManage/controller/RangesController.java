package com.alitbit.sizeManage.controller;

import com.alitbit.sizeManage.bean.Ranges;
import com.alitbit.sizeManage.exception.Exceptions;
import com.alitbit.sizeManage.service.RangesService;
import com.alitbit.sizeManage.util.ResultVOUtil;
import com.alitbit.sizeManage.vo.ResultVO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ranges")
public class RangesController {

    @Autowired
    private RangesService rangesService;

    @GetMapping("/able")
    public ResultVO able(
            @RequestParam String size
    ){
        return ResultVOUtil.success(rangesService.findAbleRanges(size));
    }

    @GetMapping("/unable")
    public ResultVO unable(
            @RequestParam String size
    ){
        return ResultVOUtil.success(rangesService.findUnableRanges(size));
    }

    @GetMapping("/all")
    public ResultVO all(
            @RequestParam String size
    ){
        return ResultVOUtil.success(rangesService.findAllRanges(size));
    }

    @PostMapping("/update")
    public ResultVO update(
            @RequestParam String rangeStr
    ){
        List<Ranges> ranges;
        try {
            ranges = new Gson().fromJson(rangeStr,
                    new TypeToken<List<Ranges>>() {
                    }.getType());
        } catch (Exception e) {
            throw new Exceptions("保存设置失败，参数错误");
        }
        return ResultVOUtil.success(rangesService.save(ranges));
    }
}
