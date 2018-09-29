package com.alitbit.sizeManage.service.impl;

import com.alitbit.sizeManage.bean.Setting;
import com.alitbit.sizeManage.dao.SettingDao;
import com.alitbit.sizeManage.service.CustomerInfoService;
import com.alitbit.sizeManage.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.InvocationTargetException;
import java.util.NoSuchElementException;

@Service
public class SettingServiceImpl implements SettingService {

    @Autowired
    private SettingDao settingDao;

    @Autowired
    private CustomerInfoService customerInfoService;

    @Override
    public boolean isAvgOpen() {
        Setting setting = null;
        try {
            setting = settingDao.findById("avg-size").get();
        }catch (NoSuchElementException e){
        }

        if (setting!=null && "1".equals(setting.getValue())){
                return true;
        }
        return false;
    }

    @Override
    @Transactional
    public void updateAvg(Boolean open) {
        Setting setting = open ? new Setting("avg-size", "1") : new Setting("avg-size", "0");
        settingDao.save(setting);

        customerInfoService.reGenerate();
    }
}
