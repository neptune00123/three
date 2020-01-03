package com.baizhi.service;

import com.baizhi.entity.Banner;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface BannerService {
    Map<String,Object> queryall(Integer rows, Integer page);
    void insert(Banner banner);
    void update(String id,String img);
    void updateall(Banner banner);
    Banner querybyid(String id);
    void delete(String[] ids);
}
