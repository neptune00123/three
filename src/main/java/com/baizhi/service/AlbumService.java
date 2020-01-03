package com.baizhi.service;

import com.baizhi.entity.Chapter;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Service
public interface AlbumService {
    Map<String,Object> queryallmain(Integer rows, Integer page);
    Map<String,Object> queryallson(Integer rows, Integer page,String album_id);
    void insert(Chapter chapter);
    void update(Chapter chapter);
    void delete(String[] ids);
    Chapter querybyid(String id);
    void download(HttpSession session, String id, HttpServletResponse response);
}
