package com.alitbit.sizeManage.service.impl;

import com.alitbit.sizeManage.bean.Trial;
import com.alitbit.sizeManage.bean.TrialAccess;
import com.alitbit.sizeManage.dao.TrialDao;
import com.alitbit.sizeManage.exception.Exceptions;
import com.alitbit.sizeManage.service.CustomerInfoService;
import com.alitbit.sizeManage.service.TrialService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
public class TrialServiceImpl implements TrialService {

    @Autowired
    private TrialDao trialDao;

    @Autowired
    private CustomerInfoService customerInfoService;

    @Override
    public List<Trial> findEnableTrials() {
        return trialDao.findByJudgeOrderByOrdersDesc(true);
    }

    @Override
    public List<Trial> findEnableTrialsOrderByASC() {
        return trialDao.findByJudgeOrderByOrdersAsc(true);
    }

    @Override
    public List<Trial> findList() {
        return trialDao.findAll();
    }

    @Override
    @Transactional
    public List<Trial> updateAbleTrial(List<Trial> trials) {

        for (Trial trial : trials){
            List<TrialAccess> trialAccesses = trial.getTrialAccesses();
            if (!CollectionUtils.isEmpty(trialAccesses)){
                for (int i=0; i<trialAccesses.size()-1; i++){
                    BigDecimal left = trialAccesses.get(i).getUp();
                    BigDecimal right = trialAccesses.get(i+1).getDown();
                    if (left.compareTo(right) >= 0){
                        throw new Exceptions("右边范围值下限不能大于左边范围值下限");
                    }
                }
            }
        }

        for (Trial trial : trials){
            List<TrialAccess> trialAccesses = trial.getTrialAccesses();
            if (!CollectionUtils.isEmpty(trialAccesses)){
                for (TrialAccess trialAccess : trialAccesses){
                    if (trialAccess.getDown().compareTo(trialAccess.getUp()) > 0){
                        throw new Exceptions("范围值上限不能低于下限");
                    }
                    trialAccess.setTrial(trial);
                }
            }
        }

        trialDao.resetOrderAndJudge();

        List<Trial> result = trialDao.saveAll(trials);

        trialDao.clearNull();

        customerInfoService.reGenerate();

        return result;
    }
}
