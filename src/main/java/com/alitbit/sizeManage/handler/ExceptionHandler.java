package com.alitbit.sizeManage.handler;

import com.alitbit.sizeManage.enums.ResultEnum;
import com.alitbit.sizeManage.exception.Exceptions;
import com.alitbit.sizeManage.util.ResultVOUtil;
import com.alitbit.sizeManage.vo.ResultVO;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(Exceptions.class)
    @ResponseBody
    public ResultVO handleException(Exceptions e){
        ResultVO resultVO;

        if (e.getCode() == ResultEnum.ERROR.getCode()){
            resultVO = ResultVOUtil.error(e.getMessage());
        }else if(e.getCode() == ResultEnum.NO_LOGIN.getCode()) {
            resultVO = ResultVOUtil.noLogin();
        }else {
            resultVO = ResultVOUtil.error("内部错误");
        }

        return resultVO;
    }


    @org.springframework.web.bind.annotation.ExceptionHandler(BindException.class)
    @ResponseBody
    public ResultVO handleGuestException(BindException e){
        return ResultVOUtil.error(e.getAllErrors().get(0).getDefaultMessage());
    }
}
