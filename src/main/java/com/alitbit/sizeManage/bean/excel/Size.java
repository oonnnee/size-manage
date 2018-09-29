package com.alitbit.sizeManage.bean.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Size {

    @Excel(name = "码数", width = 30)
    private String bigSize; //生成的尺寸
    @Excel(name = "淘宝名", width = 25)
    private String tbName; //淘宝名
    @Excel(name = "手机号码", width = 16)
    private String phone;
    @Excel(name = "身高", type = 10)
    private BigDecimal height; //身高
    @Excel(name = "平时码数")
    private String smallSize; //淘宝上尺寸
    @Excel(name = "颈侧点-膝盖", type = 10, width = 15)
    private BigDecimal neckKnee; //颈侧点-膝盖
    @Excel(name = "1/2肩宽+袖长", type = 10, width = 15)
    private BigDecimal shoulderSleeve; //1/2肩宽+袖长
    @Excel(name = "胸围", type = 10)
    private BigDecimal chest; //胸围
    @Excel(name = "腰围", type = 10)
    private BigDecimal waist; //腰围
    @Excel(name = "臀围", type = 10)
    private BigDecimal ass; //臀围
    @Excel(name = "袖肥", type = 10)
    private BigDecimal sleeveFat; //袖肥
    @Excel(name = "颈围", type = 10)
    private BigDecimal neck; //颈围
    @Excel(name = "肩宽", type = 10)
    private BigDecimal shoulder; //肩宽
    @Excel(name = "胸距", type = 10)
    private BigDecimal chestDistance; //胸距
    @Excel(name = "上胸围", type = 10)
    private BigDecimal chestUp; //上胸围
    @Excel(name = "前胸宽", type = 10)
    private BigDecimal chestFront; //前胸宽
    @Excel(name = "后背宽", type = 10)
    private BigDecimal backWidth; //后背宽
    @Excel(name = "背长", type = 10)
    private BigDecimal backLength; //背长
    @Excel(name = "臂长", type = 10)
    private BigDecimal armLength; //臂长
    @Excel(name = "上臂长", type = 10)
    private BigDecimal armUpLength; //上臂长
    @Excel(name = "袖长", type = 10)
    private BigDecimal sleeveLength; //袖长
    @Excel(name = "臂围", type = 10)
    private BigDecimal armWidth; //臂围
    @Excel(name = "袖笼围", type = 10)
    private BigDecimal sleeveWidth; //袖笼围
    @Excel(name = "肘围", type = 10)
    private BigDecimal elbowWidth; //肘围
    @Excel(name = "手腕围", type = 10)
    private BigDecimal wristWidth; //手腕围
    @Excel(name = "通档", type = 10)
    private BigDecimal pants; //通档
    @Excel(name = "大腿根围", type = 10)
    private BigDecimal bigLagWidth; //大腿根围
    @Excel(name = "膝围", type = 10)
    private BigDecimal kneeWidth; //膝围
    @Excel(name = "小腿围", type = 10)
    private BigDecimal smallLagWidth; //小腿围
    @Excel(name = "脚踝围", type = 10)
    private BigDecimal ankleWidth; //脚踝围
    @Excel(name = "胸高", type = 10)
    private BigDecimal chestHeight; //胸高
    @Excel(name = "腰高", type = 10)
    private BigDecimal wristHeight; //腰高
    @Excel(name = "后中长", type = 10)
    private BigDecimal backMidLength; //后中长
    @Excel(name = "中腰-膝盖距离", type = 10, width = 15)
    private BigDecimal wristKnee; //中腰-膝盖距离
    @Excel(name = "第七颈椎点-大腿跟围", type = 10, width = 20)
    private BigDecimal seventhLag; //第七颈椎点-大腿跟围

}
