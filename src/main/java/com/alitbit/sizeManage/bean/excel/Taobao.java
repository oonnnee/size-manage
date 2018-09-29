package com.alitbit.sizeManage.bean.excel;


import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Taobao {

    @Excel(name = "买家会员名", orderNum = "1")
    private String tbName;

    @Excel(name = "联系手机", orderNum = "18")
    private String phone;

}
