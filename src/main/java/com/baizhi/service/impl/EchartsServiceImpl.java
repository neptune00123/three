package com.baizhi.service.impl;

import com.baizhi.dao.EchartsDao;
import com.baizhi.service.EchartsService;
import com.baizhi.vo.Month;
import com.baizhi.vo.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class EchartsServiceImpl implements EchartsService {
    @Autowired
    private EchartsDao echartsDao;

    @Override
    public List<Integer> query() {
        ArrayList<Integer> days = new ArrayList<>();
        Integer querymon = echartsDao.querymon();
        Integer querytue = echartsDao.querytue();
        Integer querywed = echartsDao.querywed();
        Integer querythu = echartsDao.querythu();
        Integer queryfir = echartsDao.queryfir();
        Integer querysat = echartsDao.querysat();
        Integer querysun = echartsDao.querysun();
        days.add(querymon);
        days.add(querytue);
        days.add(querywed);
        days.add(querythu);
        days.add(queryfir);
        days.add(querysat);
        days.add(querysun);
        return days;
    }

    @Override
    public List<Integer> querymonth() {

        ArrayList<Integer> mo = new ArrayList<>();
        mo.add(echartsDao.querymonth("1"));
        mo.add(echartsDao.querymonth("2"));
        mo.add(echartsDao.querymonth("3"));
        mo.add(echartsDao.querymonth("4"));
        mo.add(echartsDao.querymonth("5"));
        mo.add(echartsDao.querymonth("6"));
        mo.add(echartsDao.querymonth("7"));
        mo.add(echartsDao.querymonth("8"));
        mo.add(echartsDao.querymonth("9"));
        mo.add(echartsDao.querymonth("10"));
        mo.add(echartsDao.querymonth("11"));
        mo.add(echartsDao.querymonth("12"));
        return mo;
    }

    @Override
    public List<People> querypeople() {
        List<People> querypeople = echartsDao.querypeople();
        return querypeople;
    }
}
