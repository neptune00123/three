package com.baizhi.controller;

import com.alibaba.fastjson.JSON;
import com.baizhi.service.EchartsService;
import com.baizhi.vo.Month;
import com.baizhi.vo.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/echarts")
public class EchartsController {
    @Autowired
    private EchartsService echartsService;

    @RequestMapping("days")
    public List<Integer> query(){
        return echartsService.query();
    }
    @RequestMapping("month")
    public List<Integer> querymonth(){
        List<Integer> list = echartsService.querymonth();
        return list;
    }
@RequestMapping("getdata")
    public List<People> querypeople(){
        List<People> querypeople = echartsService.querypeople();
        return querypeople;
    }

}
