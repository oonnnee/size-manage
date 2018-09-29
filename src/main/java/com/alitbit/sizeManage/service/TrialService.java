package com.alitbit.sizeManage.service;

import com.alitbit.sizeManage.bean.Trial;

import java.util.List;

public interface TrialService {
    List<Trial> findEnableTrials();
    List<Trial> findEnableTrialsOrderByASC();
    List<Trial> findList();
    List<Trial> updateAbleTrial(List<Trial> trials);
}
