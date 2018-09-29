package com.alitbit.sizeManage.bean.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestData {

    @Excel(name = "买家会员名", orderNum = "0")
    private String tbName;

    @Excel(name = "联系手机", orderNum = "1")
    private String phone;

    @Excel(name = "颈测点-膝盖", orderNum = "2")
    private BigDecimal neckKnee;

    @Excel(name = "1/2肩宽+袖长", orderNum = "3")
    private BigDecimal shoulderSleeve;

    @Excel(name = "胸围", orderNum = "4")
    private BigDecimal chest;

    @Excel(name = "腰围", orderNum = "5")
    private BigDecimal waist;

    @Excel(name = "臀围", orderNum = "6")
    private BigDecimal ass;

    @Excel(name = "袖肥（上臀围）", orderNum = "7")
    private BigDecimal sleeveFat;

}
