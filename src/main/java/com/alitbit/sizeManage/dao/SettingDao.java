package com.alitbit.sizeManage.dao;

import com.alitbit.sizeManage.bean.Setting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SettingDao extends JpaRepository<Setting, String> {

}
