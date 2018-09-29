package com.alitbit.sizeManage.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Trial {

    @Id
    private Integer id;
    private String name;
    private Integer orders;
    private boolean judge;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "trial_id")
    private List<TrialAccess> trialAccesses;

}
