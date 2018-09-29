package com.alitbit.sizeManage.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ResultEnum {

    SUCCESS(0, "成功"),
    ERROR(1, "错误"),
    NO_LOGIN(2, "未登录");

    private Integer code;

    private String note;

}
