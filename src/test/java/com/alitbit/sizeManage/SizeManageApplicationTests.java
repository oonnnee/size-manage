package com.alitbit.sizeManage;

import com.alitbit.sizeManage.bean.CustomerInfo;
import com.alitbit.sizeManage.bean.Trial;
import com.alitbit.sizeManage.bean.excel.Size;
import com.alitbit.sizeManage.dao.CustomerInfoDao;
import com.alitbit.sizeManage.dao.TrialDao;
import com.alitbit.sizeManage.service.CustomerInfoService;
import com.alitbit.sizeManage.service.TrialService;
import com.alitbit.sizeManage.util.MyUtil;
import com.alitbit.sizeManage.util.PageUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SizeManageApplicationTests {

    @Autowired
    private TrialDao trialDao;

    @Autowired
    private TrialService trialService;

    @Autowired
    private CustomerInfoService customerInfoService;

    @Autowired
    private CustomerInfoDao customerInfoDao;

    @Test
    public void contextLoads() {
        Page<CustomerInfo> infos = customerInfoDao.findByBigSizeNullOrderByDateDesc(PageUtil.getPageable(1, 10));
    }

}
