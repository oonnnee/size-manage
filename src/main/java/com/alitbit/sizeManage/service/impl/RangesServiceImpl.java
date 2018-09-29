package com.alitbit.sizeManage.service.impl;

import com.alitbit.sizeManage.bean.Ranges;
import com.alitbit.sizeManage.dao.RangesDao;
import com.alitbit.sizeManage.exception.Exceptions;
import com.alitbit.sizeManage.service.CustomerInfoService;
import com.alitbit.sizeManage.service.RangesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RangesServiceImpl implements RangesService {

    @Autowired
    private RangesDao rangesDao;

    @Autowired
    private CustomerInfoService customerInfoService;

    @Override
    public List<Ranges> findEnableRangesS() {
        return rangesDao.findByJudgeAndSize(true, "S");
    }

    @Override
    public List<Ranges> findEnableRangesM() {
        return rangesDao.findByJudgeAndSize(true, "M");
    }

    @Override
    public List<Ranges> findEnableRangesL() {
        return rangesDao.findByJudgeAndSize(true, "L");
    }

    @Override
    public List<Ranges> findAbleRanges(String size) {
        return rangesDao.findByJudgeAndSize(true, size.toUpperCase());
    }

    @Override
    public List<Ranges> findUnableRanges(String size) {
        return rangesDao.findByJudgeAndSize(false, size.toUpperCase());
    }

    @Override
    public List<Ranges> findAllRanges(String size) {
        return rangesDao.findBySize(size.toUpperCase());
    }

    @Override
    public Ranges save(Ranges ranges) {
        if (ranges.getDown().compareTo(ranges.getUp()) > 0){
            throw new Exceptions("范围值上限不能低于下限");
        }
        return rangesDao.save(ranges);
    }

    @Override
    public List<Ranges> save(List<Ranges> ranges) {
        for (Ranges range : ranges){
            if (range.getDown().compareTo(range.getUp()) > 0){
                throw new Exceptions("范围值上限不能低于下限");
            }
        }

        List<Ranges> result = rangesDao.saveAll(ranges);

        customerInfoService.reGenerate();

        return result;
    }
}
