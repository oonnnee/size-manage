package com.alitbit.sizeManage.service.impl;

import com.alitbit.sizeManage.bean.CustomerInfo;
import com.alitbit.sizeManage.bean.Ranges;
import com.alitbit.sizeManage.bean.Trial;
import com.alitbit.sizeManage.bean.TrialAccess;
import com.alitbit.sizeManage.dao.CustomerInfoDao;
import com.alitbit.sizeManage.dto.PageChunk;
import com.alitbit.sizeManage.service.CustomerInfoService;
import com.alitbit.sizeManage.service.RangesService;
import com.alitbit.sizeManage.service.SettingService;
import com.alitbit.sizeManage.service.TrialService;
import com.alitbit.sizeManage.util.MyUtil;
import com.alitbit.sizeManage.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerInfoServiceImpl implements CustomerInfoService {

    @Autowired
    private CustomerInfoDao customerInfoDao;

    @Autowired
    private SettingService settingService;
    @Autowired
    private TrialService trialService;
    @Autowired
    private RangesService rangesService;

    @Override
    public CustomerInfo save(CustomerInfo customerInfo) {
        CustomerInfo saveResult = customerInfoDao.save(customerInfo);

        return saveResult;
    }

    @Override
    public List<CustomerInfo> save(List<CustomerInfo> customerInfos) {
        return customerInfoDao.saveAll(customerInfos);
    }

    @Override
    public PageChunk<CustomerInfo> findPage(Integer page, Integer size, String filter, String value) {
        Pageable pageable = PageUtil.getPageable(page, size);

        Page<CustomerInfo> customerInfoPage;

        if ("".equals(value.trim())){
            customerInfoPage = customerInfoDao.findByOrderByDateDesc(pageable);
        }else {
            if ("phone".equals(filter)){
                customerInfoPage = customerInfoDao.findByPhoneLikeOrderByDateDesc(pageable, "%"+value+"%");
            }else if ("smallSize".equals(filter)){
                customerInfoPage = customerInfoDao.findBySmallSizeOrderByDateDesc(pageable,value.toUpperCase());
            }else if ("bigSize".equals(filter)){
                customerInfoPage = customerInfoDao.findByBigSizeLikeOrderByDateDesc(pageable,"%"+value+"%");
            }else if ("tbName".equals(filter)){
                customerInfoPage = customerInfoDao.findByTbNameLikeOrderByDateDesc(pageable,"%"+value+"%");
            }else {
                customerInfoPage = customerInfoDao.findByOrderByDateDesc(pageable);
            }
        }

        return PageUtil.getPageChunk(customerInfoPage);
    }

    @Override
    public PageChunk<CustomerInfo> findPageProcess(Integer page, Integer size, String filter, String value) {
        Pageable pageable = PageUtil.getPageable(page, size);

        Page<CustomerInfo> customerInfoPage;

        if ("".equals(value.trim())){
            customerInfoPage = customerInfoDao.findByBigSizeNotNullOrderByDateDesc(pageable);
        }else {
            if ("phone".equals(filter)){
                customerInfoPage = customerInfoDao.findByPhoneLikeAndBigSizeNotNullOrderByDateDesc(pageable, "%"+value+"%");
            }else if ("smallSize".equals(filter)){
                customerInfoPage = customerInfoDao.findBySmallSizeAndBigSizeNotNullOrderByDateDesc(pageable,value.toUpperCase());
            }else if ("bigSize".equals(filter)){
                customerInfoPage = customerInfoDao.findByBigSizeLikeOrderByDateDesc(pageable,"%"+value+"%");
            }else if ("tbName".equals(filter)){
                customerInfoPage = customerInfoDao.findByTbNameLikeAndBigSizeNotNullOrderByDateDesc(pageable,"%"+value+"%");
            }else {
                customerInfoPage = customerInfoDao.findByBigSizeNotNullOrderByDateDesc(pageable);
            }
        }

        return PageUtil.getPageChunk(customerInfoPage);
    }

    @Override
    public PageChunk<CustomerInfo> findPageRaw(Integer page, Integer size, String filter, String value) {
        Pageable pageable = PageUtil.getPageable(page, size);

        Page<CustomerInfo> customerInfoPage;

        if ("".equals(value.trim())){
            customerInfoPage = customerInfoDao.findByBigSizeNullOrderByDateDesc(pageable);
        }else {
            if ("phone".equals(filter)){
                customerInfoPage = customerInfoDao.findByPhoneLikeAndBigSizeNullOrderByDateDesc(pageable, "%"+value+"%");
            }else if ("smallSize".equals(filter)){
                customerInfoPage = customerInfoDao.findBySmallSizeAndBigSizeNullOrderByDateDesc(pageable,value.toUpperCase());
            }else if ("tbName".equals(filter)){
                customerInfoPage = customerInfoDao.findByTbNameLikeAndBigSizeNullOrderByDateDesc(pageable,"%"+value+"%");
            }else {
                customerInfoPage = customerInfoDao.findByBigSizeNullOrderByDateDesc(pageable);
            }
        }

        return PageUtil.getPageChunk(customerInfoPage);
    }

    @Override
    public List<CustomerInfo> findAll() {
        return customerInfoDao.findAll();
    }

    @Override
    public List<CustomerInfo> findAllProcess() {
        return customerInfoDao.findByBigSizeNotNullOrderByDateDesc();
    }

    @Override
    public List<CustomerInfo> findAllRaw() {
        return customerInfoDao.findByBigSizeNullOrderByDateDesc();
    }

    @Override
    public void clearBigSize() {
        customerInfoDao.clearBigSize();
    }

    @Override
    public void generate(List<CustomerInfo> customerInfos)  {
        Map<String, Integer> sizeMap = MyUtil.getSizeMap();
        List<Trial> enableTrials = trialService.findEnableTrials();
        boolean avgOpen = settingService.isAvgOpen();
        Map<String, List<Ranges>> enableRangesMap = new HashMap<>();
        enableRangesMap.put("S", rangesService.findEnableRangesS());
        enableRangesMap.put("M", rangesService.findEnableRangesM());
        enableRangesMap.put("L", rangesService.findEnableRangesL());

        for (CustomerInfo customerInfo : customerInfos){
            String smallSize = customerInfo.getSmallSize().toUpperCase();
            List<Ranges> enableRanges = enableRangesMap.get(smallSize);
            boolean isNormal = true;
            if (enableRanges != null) {
                for (Ranges enableRange : enableRanges) {
                    Method method = null;
                    try {
                        method = MyUtil.getMethod(enableRange.getName());
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                    BigDecimal data = null;
                    try {
                        data = (BigDecimal) method.invoke(customerInfo);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                    if (data==null || data.compareTo(enableRange.getDown()) < 0 || data.compareTo(enableRange.getUp()) > 0) {
                        customerInfo.setBigSize(enableRange.getName() + "数据异常");
                        isNormal = false;
                        break;
                    }
                }
            }

            if (isNormal){
                int size = 1;
                int product = 1;
                for (Trial trial : enableTrials){
                    List<TrialAccess> trialAccesses = trial.getTrialAccesses();
                    Method method = null;
                    try {
                        method = MyUtil.getMethod(trial.getName());
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                    BigDecimal data = null;
                    try {
                        data = (BigDecimal) method.invoke(customerInfo);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }

                    int orders = 0;
                    for (TrialAccess trialAccess : trialAccesses){
                        if (data.compareTo(trialAccess.getDown())>=0 && data.compareTo(trialAccess.getUp())<=0){
                            orders = trialAccess.getOrders();
                            break;
                        }
                    }
                    if (orders > 0){
                        size += product * (orders-1);
                    }else {
                        customerInfo.setBigSize(trial.getName() + "数据异常");
                        isNormal = false;
                        break;
                    }

                    product = product * trialAccesses.size();
                }

                if (isNormal){
                    if (!avgOpen){
                        size += product * sizeMap.get(customerInfo.getSmallSize());
                    }
                    customerInfo.setBigSize(size+"");
                }
            }

        }

        this.save(customerInfos);
    }

    @Override
    public void reGenerate() {
        List<CustomerInfo> customerInfos = this.findAllProcess();
        this.generate(customerInfos);
    }

}
