package com.baizhi.service.impl;

import com.baizhi.dao.BannerDao;
import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BannerServiceImpl implements BannerService
{
    @Autowired
    private BannerDao bannerDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String,Object> queryall(Integer rows, Integer page) {
        //起始页数（可以从0开始）
        Integer start = (page - 1) * rows;
        //总条数
        Integer count = bannerDao.querycount();
        //数据获取
        List<Banner> queryall = bannerDao.queryall(start, rows);
        for (Banner banner : queryall) {
            System.out.println(banner+"-------");
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
    public void insert(Banner banner) {
        bannerDao.insert(banner);
    }

    @Override
    public void update(String id, String img) {
        bannerDao.update(id,img);
    }

    @Override
    public void updateall(Banner banner) {
        bannerDao.updateall(banner);
    }

    @Override
    public Banner querybyid(String id) {
        return bannerDao.querybyid(id);
    }

    @Override
    public void delete(String[] ids) {
        bannerDao.delete(ids);
    }
}
