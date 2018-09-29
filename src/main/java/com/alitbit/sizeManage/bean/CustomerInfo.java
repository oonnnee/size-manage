package com.alitbit.sizeManage.bean;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
public class CustomerInfo {

    @Id
    private String phone;
    private String tbName; //淘宝名
    private Date date;
    private String smallSize; //淘宝上尺寸
    private String bigSize; //生成的尺寸
    private BigDecimal height; //身高
    private BigDecimal neckKnee; //颈侧点-膝盖
    private BigDecimal shoulderSleeve; //1/2肩宽+袖长
    private BigDecimal chest; //胸围
    private BigDecimal waist; //腰围
    private BigDecimal ass; //臀围
    private BigDecimal sleeveFat; //袖肥
    private BigDecimal neck; //颈围
    private BigDecimal shoulder; //肩宽
    private BigDecimal chestDistance; //胸距
    private BigDecimal chestUp; //上胸围
    private BigDecimal chestFront; //前胸宽
    private BigDecimal backWidth; //后背宽
    private BigDecimal backLength; //背长
    private BigDecimal armLength; //臂长
    private BigDecimal armUpLength; //上臂长
    private BigDecimal sleeveLength; //袖长
    private BigDecimal armWidth; //臂围
    private BigDecimal sleeveWidth; //袖笼围
    private BigDecimal elbowWidth; //肘围
    private BigDecimal wristWidth; //手腕围
    private BigDecimal pants; //通档
    private BigDecimal bigLagWidth; //大腿根围
    private BigDecimal kneeWidth; //膝围
    private BigDecimal smallLagWidth; //小腿围
    private BigDecimal ankleWidth; //脚踝围
    private BigDecimal chestHeight; //胸高
    private BigDecimal wristHeight; //腰高
    private BigDecimal backMidLength; //后中长
    private BigDecimal wristKnee; //中腰-膝盖距离
    private BigDecimal seventhLag; //第七颈椎点-大腿跟围

}
