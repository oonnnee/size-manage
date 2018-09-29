package com.alitbit.sizeManage.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
public class TrialAccess {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private BigDecimal down;
    private BigDecimal up;
    private Integer orders;
    @ManyToOne
    @JsonIgnore
    private Trial trial;
}
