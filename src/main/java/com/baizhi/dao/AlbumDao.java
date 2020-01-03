package com.baizhi.dao;

import com.baizhi.entity.Album;
import com.baizhi.entity.Chapter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AlbumDao {
    //分页展示所有
    List<Album> queryallmain(@Param("start") Integer start, @Param("rows") Integer rows);
    //查询总数
    Integer querycount();
    //分页展示子表
    List<Chapter> queryallson(@Param("start") Integer start, @Param("rows") Integer rows,@Param("album_id") String album_id);
    //查询子表总数
    Integer querycountson();
    //子表添加数据
    void insert(Chapter chapter);
    //子表修改数据
    void update(Chapter chapter);
    //批量删除
    void delete(String[] ids);
    //根据id查询
    Chapter querybyid(String id);
}
