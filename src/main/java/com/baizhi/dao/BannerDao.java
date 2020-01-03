package com.baizhi.dao;

import com.baizhi.entity.Banner;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BannerDao {
    //数据
    List<Banner> queryall(@Param("start") Integer start, @Param("rows") Integer rows);
    //总条数
    Integer querycount();
    //添加图片
    void insert(Banner banner);
    //修改图片路径
    void update(@Param("id")String id,@Param("img")String img);
    //修改信息
    void updateall(Banner banner);
    //根据id查询数据
    Banner querybyid(String id);
    //根据id批量删除
    void delete(String[] ids);
}
