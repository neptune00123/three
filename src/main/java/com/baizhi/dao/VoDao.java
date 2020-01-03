package com.baizhi.dao;

import com.baizhi.entity.Article;
import com.baizhi.vo.Body;
import com.baizhi.vo.Header;
import com.baizhi.vo.Wen;

import java.util.List;

public interface VoDao {
    List<Header> querya();

    List<Body> queryaa();

    List<Article> queryart(String id);

    List<Article> queryother(String id);

    Wen queryalbum(String id);

    List<com.baizhi.vo.List> querylist(String id);
}
