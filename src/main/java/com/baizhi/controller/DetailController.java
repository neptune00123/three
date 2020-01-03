package com.baizhi.controller;

import com.baizhi.dao.VoDao;
import java.util.*;

import com.baizhi.vo.Wen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("detail")
@RestController
public class DetailController {

    @Autowired
    private VoDao voDao;

@RequestMapping("wen")
    public Map<String,Object> queryall(String id){
        Map<String, Object> map = new HashMap<>();
        Wen queryalbum = voDao.queryalbum(id);
        map.put("Wen",queryalbum);
        map.put("List",voDao.querylist(id));
        return map;
    }
}
