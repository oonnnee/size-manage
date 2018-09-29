package com.alitbit.sizeManage.service.impl;

import com.alitbit.sizeManage.bean.Ranges;
import com.alitbit.sizeManage.dao.RangesDao;
import com.alitbit.sizeManage.service.RangesService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RangeServiceImplTest {

    @Autowired
    private RangesService rangesService;

    @Test
    public void test(){
        List<Ranges> enableRanges = rangesService.findEnableRangesS();
        System.out.println(enableRanges);
    }

}