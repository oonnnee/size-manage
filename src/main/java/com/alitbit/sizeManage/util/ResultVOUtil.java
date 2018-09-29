package com.alitbit.sizeManage.util;

import com.alitbit.sizeManage.enums.ResultEnum;
import com.alitbit.sizeManage.vo.ResultVO;

public class ResultVOUtil {

    public static ResultVO success(){
        return new ResultVO(ResultEnum.SUCCESS.getCode(),"成功", null);
    }

    public static ResultVO success(Object data){
        return new ResultVO(ResultEnum.SUCCESS.getCode(),"成功", data);
    }


    public static ResultVO error(String msg){
        return new ResultVO(ResultEnum.ERROR.getCode(), msg, null);
    }

    public static ResultVO noLogin(){
        return new ResultVO(ResultEnum.NO_LOGIN.getCode(), ResultEnum.NO_LOGIN.getNote(), null);
    }

}
