package com.alitbit.sizeManage.dao;

import com.alitbit.sizeManage.bean.Trial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface TrialDao extends JpaRepository<Trial, Integer> {

    List<Trial> findByJudgeOrderByOrdersDesc(boolean judge);
    List<Trial> findByJudgeOrderByOrdersAsc(boolean judge);

    @Query(value = "update trial set orders=0, judge=0", nativeQuery = true)
    @Modifying
    void resetOrderAndJudge();

    @Query(value = "delete from trial_access where trial_id is null", nativeQuery = true)
    @Modifying
    void clearNull();
}
