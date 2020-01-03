package com.baizhi.dao;

import com.baizhi.entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleDao {

    //查所有
    List<Article> queryall(@Param("start") Integer start, @Param("rows") Integer rows);
    //删除
    void delete(String[] ids);
    //查总数
    Integer querycount();
    //增加
    void insert(Article article);
    //根据id查询
    Article querybyid(String id);
    //id修改
    void update(Article article);
}
