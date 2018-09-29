package com.alitbit.sizeManage.bean;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@Entity
public class Ranges {

    @Id
    private Integer id;
    private String name;
    private BigDecimal down;
    private BigDecimal up;
    private String size;
    private boolean judge;

}
