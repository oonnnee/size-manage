package com.alitbit.sizeManage.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.alitbit.sizeManage.bean.CustomerInfo;
import com.alitbit.sizeManage.bean.Ranges;
import com.alitbit.sizeManage.bean.Trial;
import com.alitbit.sizeManage.bean.TrialAccess;
import com.alitbit.sizeManage.bean.excel.Taobao;
import com.alitbit.sizeManage.bean.excel.TestData;
import com.alitbit.sizeManage.dto.PageChunk;
import com.alitbit.sizeManage.exception.Exceptions;
import com.alitbit.sizeManage.service.CustomerInfoService;
import com.alitbit.sizeManage.service.RangesService;
import com.alitbit.sizeManage.service.SettingService;
import com.alitbit.sizeManage.service.TrialService;
import com.alitbit.sizeManage.util.MyUtil;
import com.alitbit.sizeManage.util.ResultVOUtil;
import com.alitbit.sizeManage.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/customer-info")
public class CustomerInfoController {

    @Autowired
    private CustomerInfoService customerInfoService;

    @Autowired
    private RangesService rangesService;

    @Autowired
    private TrialService trialService;

    @Autowired
    private SettingService settingService;

    @GetMapping("/page")
    public ResultVO page(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "20") Integer size,
            @RequestParam(defaultValue = "all") String filter,
            String value
    ) {
        PageChunk<CustomerInfo> pageChunk = customerInfoService.findPage(page, size, filter, value);

        return ResultVOUtil.success(pageChunk);
    }

    @GetMapping("/page-process")
    public ResultVO pageProcess(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "20") Integer size,
            @RequestParam(defaultValue = "all") String filter,
            String value
    ) {
        PageChunk<CustomerInfo> pageChunk = customerInfoService.findPageProcess(page, size, filter, value);

        return ResultVOUtil.success(pageChunk);
    }

    @GetMapping("/page-raw")
    public ResultVO pageRaw(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "20") Integer size,
            @RequestParam(defaultValue = "all") String filter,
            String value
    ) {
        PageChunk<CustomerInfo> pageChunk = customerInfoService.findPageRaw(page, size, filter, value);

        return ResultVOUtil.success(pageChunk);
    }

    @PostMapping("/upload-and-generate")
    @Transactional
    public ResultVO uploadAndGenerate(
            @RequestParam("upload_file") MultipartFile file
    ) throws Exception {
        if (file.isEmpty() || StringUtils.isEmpty(file.getOriginalFilename())) {
            throw new Exceptions("上传文件不存在");
        }
        String fileName = file.getOriginalFilename();
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            throw new Exceptions("上传文件必须为xls或xlsx格式");
        }
        List<Taobao> taobaos = ExcelImportUtil.importExcel(file.getInputStream(), Taobao.class, new ImportParams());

        boolean f = false;
        for (Taobao taobao : taobaos){
            if (taobao.getPhone()!=null && taobao.getTbName()!=null){
                f = true;
                break;
            }
        }
        if (!f){
            throw new RuntimeException("淘宝客户信息表格格式错误");
        }

        customerInfoService.clearBigSize();

        for (Taobao taobao : taobaos){
            if (taobao.getPhone()!=null && taobao.getPhone().indexOf('\'')==0){
                if (taobao.getPhone().indexOf('旧') < 0){
                    taobao.setPhone(taobao.getPhone().substring(1, taobao.getPhone().length()));
                }else {
                    taobao.setPhone(taobao.getPhone().substring(1, taobao.getPhone().indexOf('（')).trim());
                }
            }
        }

        List<CustomerInfo> customerInfos = customerInfoService.findAll();

        List<CustomerInfo> customerSaveInfos = new ArrayList<>();
        if (!CollectionUtils.isEmpty(customerInfos)){
            for (CustomerInfo customerInfo : customerInfos) {
                if (customerInfo.getPhone() == null){
                    continue;
                }
                for (Taobao taobao : taobaos) {
                    if (taobao.getPhone() == null){
                        continue;
                    }
                    if (customerInfo.getPhone().equals(taobao.getPhone())) {
                        customerSaveInfos.add(customerInfo);
                        break;
                    }
                }
            }
        }

        if (!CollectionUtils.isEmpty(customerSaveInfos)){
            customerInfoService.generate(customerSaveInfos);
        }

        return ResultVOUtil.success("生成尺码成功");
    }

    @PostMapping("/upload-test-data")
    public ResultVO uploadTestData(
            @RequestParam("upload_file") MultipartFile file
    ) throws Exception {
        List<TestData> testDatas = ExcelImportUtil.importExcel(file.getInputStream(), TestData.class, new ImportParams());

        List<CustomerInfo> customerInfos = new ArrayList<>();

        Map<Integer, String> map = new HashMap<Integer, String>();
        map.put(1, "S");
        map.put(2, "M");
        map.put(3, "L");

        for (TestData testData : testDatas) {
            CustomerInfo customerInfo = new CustomerInfo();

            BeanUtils.copyProperties(testData, customerInfo);

            int random = new Random().nextInt(3)+1;

            customerInfo.setSmallSize(map.get(random));
            customerInfo.setDate(new Date(new Date().getTime() - new Random().nextInt(1000000)));

            customerInfos.add(customerInfo);
        }

        List<CustomerInfo> result = customerInfoService.save(customerInfos);

        return ResultVOUtil.success(result);
    }
}