package com.alitbit.sizeManage.dao;

import com.alitbit.sizeManage.bean.CustomerInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface CustomerInfoDao extends JpaRepository<CustomerInfo, String> {

    Page<CustomerInfo> findByOrderByDateDesc(Pageable pageable);

    Page<CustomerInfo> findByPhoneLikeOrderByDateDesc(Pageable pageable, String pattern);
    Page<CustomerInfo> findByBigSizeLikeOrderByDateDesc(Pageable pageable, String pattern);
    Page<CustomerInfo> findByTbNameLikeOrderByDateDesc(Pageable pageable, String pattern);
    Page<CustomerInfo> findBySmallSizeOrderByDateDesc(Pageable pageable, String smallSize);

    Page<CustomerInfo> findByPhoneLikeAndBigSizeNullOrderByDateDesc(Pageable pageable, String pattern);
    Page<CustomerInfo> findByTbNameLikeAndBigSizeNullOrderByDateDesc(Pageable pageable, String pattern);
    Page<CustomerInfo> findBySmallSizeAndBigSizeNullOrderByDateDesc(Pageable pageable, String smallSize);
    Page<CustomerInfo> findByBigSizeNullOrderByDateDesc(Pageable pageable);

    Page<CustomerInfo> findByPhoneLikeAndBigSizeNotNullOrderByDateDesc(Pageable pageable, String pattern);
    Page<CustomerInfo> findByTbNameLikeAndBigSizeNotNullOrderByDateDesc(Pageable pageable, String pattern);
    Page<CustomerInfo> findBySmallSizeAndBigSizeNotNullOrderByDateDesc(Pageable pageable, String smallSize);
    Page<CustomerInfo> findByBigSizeNotNullOrderByDateDesc(Pageable pageable);

    List<CustomerInfo> findByBigSizeNotNullOrderByDateDesc();
    List<CustomerInfo> findByBigSizeNullOrderByDateDesc();


    @Modifying
    @Query(value = "update customer_info set big_size = null",  nativeQuery = true)
    void clearBigSize();
}
