package com.alitbit.sizeManage.service;

import com.alitbit.sizeManage.bean.Ranges;

import java.util.List;

public interface RangesService {

    List<Ranges> findEnableRangesS();
    List<Ranges> findEnableRangesM();
    List<Ranges> findEnableRangesL();

    List<Ranges> findAbleRanges(String size);
    List<Ranges> findUnableRanges(String size);
    List<Ranges> findAllRanges(String size);

    Ranges save(Ranges ranges);
    List<Ranges> save(List<Ranges> ranges);
}
