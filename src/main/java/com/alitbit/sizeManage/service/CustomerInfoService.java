package com.alitbit.sizeManage.service;

import com.alitbit.sizeManage.bean.CustomerInfo;
import com.alitbit.sizeManage.dto.PageChunk;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface CustomerInfoService {

    CustomerInfo save(CustomerInfo customerInfo);
    List<CustomerInfo> save(List<CustomerInfo> customerInfos);
    PageChunk<CustomerInfo> findPage(Integer page, Integer size, String filter, String value);
    PageChunk<CustomerInfo> findPageProcess(Integer page, Integer size, String filter, String value);
    PageChunk<CustomerInfo> findPageRaw(Integer page, Integer size, String filter, String value);
    List<CustomerInfo> findAll();
    List<CustomerInfo> findAllProcess();
    List<CustomerInfo> findAllRaw();

    void clearBigSize();

    void generate(List<CustomerInfo> customerInfos);

    void reGenerate();
}
