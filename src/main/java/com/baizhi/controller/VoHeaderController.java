package com.baizhi.controller;

import com.baizhi.dao.UserDao;
import com.baizhi.dao.VoDao;
import com.baizhi.entity.Article;
import com.baizhi.entity.User;
import com.baizhi.vo.Body;
import com.baizhi.vo.Header;
import com.sun.javafx.collections.MappingChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;


@RequestMapping("first_page")
@RestController
public class VoHeaderController {
    @Autowired
    private VoDao voDao;
    @Autowired
    private UserDao userDao;

    @RequestMapping("query")
    public Map<String,Object> queryarticle(String id, String type, String sub_type) {
        Map<String, Object> map = new HashMap<>();
        User s = userDao.queryid(id);
        if ("all".equals(type)) {
            map.put("header", voDao.querya());
            map.put("body", voDao.queryaa());
            map.put("ssyj", voDao.queryart(s.getGuru_id()));
        }
        if ("wen".equals(type)) {
            map.put("body", voDao.queryaa());
        }
        if ("si".equals(type)) {
            if ("ssyj".equals(sub_type)) {
                map.put("header", voDao.queryart(s.getGuru_id()));
            }
            if ("xmfy".equals(sub_type)) {
                map.put("header", voDao.queryother(s.getGuru_id()));
            }
        }
        return map;
    }



}
