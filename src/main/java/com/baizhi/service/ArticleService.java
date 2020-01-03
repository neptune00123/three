package com.baizhi.service;



import com.baizhi.entity.Article;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface ArticleService {

    //查所有
    Map<String,Object> queryall(Integer rows, Integer page);
    //删除
    void delete(String[] ids);
    //添加
    void insert(Article article);
    //id查询
    Article querybyid(String id);
    //修改
    void update(Article article);
}
