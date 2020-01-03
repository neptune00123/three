package com.baizhi.dao;

import com.baizhi.vo.Month;
import com.baizhi.vo.People;

import java.util.List;

public interface EchartsDao {
    Integer querymon();
    Integer querytue();
    Integer querywed();
    Integer querythu();
    Integer queryfir();
    Integer querysat();
    Integer querysun();
    Integer querymonth(String id);
    List<People> querypeople();
}
