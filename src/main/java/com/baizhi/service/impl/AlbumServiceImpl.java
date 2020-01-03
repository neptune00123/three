package com.baizhi.service.impl;

import com.baizhi.dao.AlbumDao;
import com.baizhi.entity.Album;
import com.baizhi.entity.Chapter;
import com.baizhi.service.AlbumService;


import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class AlbumServiceImpl implements AlbumService
{
    @Autowired
    private AlbumDao albumDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String, Object> queryallmain(Integer rows, Integer page) {
        //起始页数（可以从0开始）
        Integer start = (page - 1) * rows;
        //总条数
        Integer count = albumDao.querycount();
        //数据获取
        List<Album> queryall = albumDao.queryallmain(start, rows);
        for (Album album : queryall) {
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
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String, Object> queryallson(Integer rows, Integer page,String album_id) {
        //起始页数（可以从0开始）
        Integer start = (page - 1) * rows;
        //总条数
        Integer count = albumDao.querycountson();
        //数据获取
        List<Chapter> queryall = albumDao.queryallson(start, rows,album_id);
        for (Chapter chapter : queryall) {
            System.out.println(chapter+"---+++----");
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
    public void insert(Chapter chapter) {
        albumDao.insert(chapter);
    }

    @Override
    public void update(Chapter chapter) {
        albumDao.update(chapter);
    }

    @Override
    public void delete(String[] ids) {
        albumDao.delete(ids);
    }

    @Override
    public Chapter querybyid(String id) {
        return albumDao.querybyid(id);
    }

    @Override
    public void download(HttpSession session, String id, HttpServletResponse response) {
        Chapter querybyid = querybyid(id);
        String src = querybyid.getSrc();
        String realPath = session.getServletContext().getRealPath("/music");
        //定义文件
        File file = new File(realPath, src);
        //设置头文件
        response.setHeader("content-disposition","attachment;fileName="+src);
        ServletOutputStream outputStream = null;
        try {
            //获取输出流
            outputStream = response.getOutputStream();
            //下载
            FileUtils.copyFile(file,outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                //关闭输出流
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
