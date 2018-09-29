package com.alitbit.sizeManage.dao;

import com.alitbit.sizeManage.bean.Ranges;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RangesDao extends JpaRepository<Ranges, Integer> {

    List<Ranges> findByJudgeAndSize(boolean judge, String size);

    List<Ranges> findBySize(String size);
}
