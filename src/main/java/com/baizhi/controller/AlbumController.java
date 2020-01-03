package com.baizhi.controller;

import com.baizhi.entity.Chapter;
import com.baizhi.service.AlbumService;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("Album")
public class AlbumController {

    @Autowired
    private AlbumService albumService;



    @RequestMapping("queryallmain")
    public Map<String, Object> queryallbanner(Integer rows, Integer page) {
        return albumService.queryallmain(rows, page);
    }
    @RequestMapping("queryallson")
    public Map<String, Object> queryallson(Integer rows, Integer page ,String album_id) {
        System.out.println(album_id+"//////");
        return albumService.queryallson(rows, page,album_id);
    }

    @RequestMapping("music")
    public Map<String, Object> edit(String oper, Chapter chapter, String[] id,String album_id) {
        Map<String, Object> map = new HashMap<>();
        if ("add".equals(oper)) {
            //新增数据
            chapter.setId(UUID.randomUUID().toString());
            chapter.setTitle(chapter.getTitle());
            chapter.setSrc("null");
            chapter.setAlbum_id(album_id);
            chapter.setDuration("1:30");
            chapter.setSize("10.5MB");
            chapter.setStatus(chapter.getStatus());

            albumService.insert(chapter);
            System.out.println(chapter + "------");
            map.put("id", chapter.getId());
        } else if ("edit".equals(oper)) {

            chapter.setId(chapter.getId());
            chapter.setTitle(chapter.getTitle());
            chapter.setStatus(chapter.getStatus());
            if (chapter.getSrc() == "") {
                chapter.setSrc(null);
                albumService.update(chapter);
                map.put("msg","修改成功");
            } else {
                albumService.update(chapter);
                map.put("msg","修改成功");
                map.put("id",chapter.getId());
            }
            albumService.update(chapter);
            System.out.println(chapter + "*******---");
            map.put("id", chapter.getId());
        }else if ("del".equals(oper)) {
            albumService.delete(id);
        }
        return map;
    }
    @RequestMapping("download")
    public void download(String id, HttpSession session, HttpServletResponse response){
        albumService.download(session,id,response);
    }


    @RequestMapping("AlbumUpload")
    public void upload(MultipartFile src, String id, HttpSession session) {
        //1. 获得 upload的路径
        String realPath = session.getServletContext().getRealPath("/music");
        //2.判断文件夹是否存在
        File file1 = new File(realPath);
        if (!file1.exists()) {
            file1.mkdirs();
        }
        //3.获取文件真实名字
        String filename = src.getOriginalFilename();

        //4. 为了防止同一个文件多次上传发生覆盖  拼接时间戳
        String realname = new Date().getTime() + "_" + filename;

        //5.文件上传
        try {
            src.transferTo(new File(realPath, realname));
            Chapter chapter = new Chapter();
            chapter.setId(id);
            chapter.setSrc(realname);
            //获取文件时间
            Long duration = getDuration(new File(realPath+"\\"+realname));
            chapter.setDuration((duration/60+":"+duration%60));
            //获取文件大小
            long size = src.getSize();
            DecimalFormat df = new DecimalFormat("0.00");
            String num = df.format((float)size/1024/1024);
            chapter.setSize(num+"MB");

            albumService.update(chapter);
            System.out.println(chapter+"~~~~~~~");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Long getDuration(File source) {
        Encoder encoder = new Encoder();
        long ls = 0;
        MultimediaInfo m;
        try {
            m = encoder.getInfo(source);
            ls = m.getDuration()/1000;

        } catch (Exception e) {
            System.out.println("获取音频时长有误：" + e.getMessage());
        }
        return ls;
    }
    @RequestMapping("fileedit")
    public void bannerUpload(MultipartFile src, String id, HttpSession session) {
        String realPath = session.getServletContext().getRealPath("/img");
        File file = new File(realPath);
        if(!file.exists()){
            file.mkdirs();
        }
        String realName =  src.getOriginalFilename();
        String name = new Date().getTime()+"_"+realName;
        try {
            src.transferTo(new File(realPath,name));
            Chapter chapter = new Chapter();
            chapter.setId(id);
            Long duration = getDuration(new File(realPath+"\\"+name));
            chapter.setDuration((duration/60+":"+duration%60));
            DecimalFormat df = new DecimalFormat("0.00");
            long size = src.getSize();
            String num = df.format((float)size/1024/1024);
            chapter.setSize(num+"MB");
            chapter.setSrc(name);

            albumService.update(chapter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @RequestMapping("querybyid")
    public Chapter querybyid(String id){
        Chapter querybyid = albumService.querybyid(id);
        return querybyid;
    }
}
