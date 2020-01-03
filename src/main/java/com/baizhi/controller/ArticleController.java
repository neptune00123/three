package com.baizhi.controller;

import com.baizhi.entity.Article;
import com.baizhi.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("Article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping("queryall")
    public Map<String,Object> queryall(Integer rows, Integer page){
        Map<String, Object> map = articleService.queryall(rows, page);
        return map;
    }

    public Map<String, Object> edit(String oper, String[] id, Article article){
        HashMap<String, Object> map = new HashMap<>();
        if ("del".equals(oper)){
            articleService.delete(id);
        }

        return map;
    }
}
