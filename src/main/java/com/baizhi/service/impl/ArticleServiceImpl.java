package com.baizhi.service.impl;

import com.baizhi.dao.ArticleDao;
import com.baizhi.entity.Article;
import com.baizhi.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDao articleDao;

    @Override
    public Map<String, Object> queryall(Integer rows, Integer page) {
        //起始页数（可以从0开始）
        Integer start = (page - 1) * rows;
        //总条数
        Integer count = articleDao.querycount();
        //数据获取
        List<Article> queryall = articleDao.queryall(start, rows);
        for (Article album : queryall) {
            System.out.println(album+"-------");
        }
        //计算总页数
        Integer totalPage = count % rows == 0 ? count / rows : count / rows + 1;
        //将数据放进map集合
        HashMap<String, Object> map = new HashMap<>();
        map.put("page", page);
        map.put("rows", queryall);
        map.put("total", totalPage);
        map.put("records", count);
        return map;
    }

    @Override
    public void delete(String[] ids) {
        articleDao.delete(ids);
    }

    @Override
    public void insert(Article article) {
        article.setId(UUID.randomUUID().toString());
        article.setCreate_date(new Date());
        articleDao.insert(article);
    }

    @Override
    public Article querybyid(String id) {
        return articleDao.querybyid(id);
    }

    @Override
    public void update(Article article) {
        article.setCreate_date(new Date());
        articleDao.update(article);
    }
}
