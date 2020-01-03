package com.baizhi.service;

import com.baizhi.vo.Month;
import com.baizhi.vo.People;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EchartsService {
    List<Integer> query();

    List<Integer> querymonth();

    List<People> querypeople();
}
