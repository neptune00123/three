package com.baizhi.controller;



import com.baizhi.entity.Article;
import com.baizhi.service.ArticleService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/kindeditor")
public class KindEditorController {
    @Autowired
    private ArticleService articleService;


    @RequestMapping("uploadImg")
    public Map<String, Object> uploadImg(MultipartFile img, HttpSession session, HttpServletRequest request) {
        System.out.println("++++++++++++");

        Map<String, Object> map = new HashMap<>();
        String realPath = session.getServletContext().getRealPath("/img");
        File file = new File(realPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        //文件名
        String originalFilename = img.getOriginalFilename();
        String name = new Date().getTime() + "_" + originalFilename;
        try {
            //文件上传
            img.transferTo(new File(realPath, name));
            /*
             * {"error":0, "url":"http://localhost:9992/cmfz_zzh/img/xxx.png" }
             * */
            map.put("error", 0);
            String scheme = request.getScheme();                  //http

            InetAddress localHost = InetAddress.getLocalHost();  //localhost
            //PC-20190718ZLAM   /  192.168.1.156
            //获取ip地址
            String localhost = localHost.toString().split("/")[1];
            int serverPort = request.getServerPort();           //端口号port
            String contextPath = request.getContextPath();      //项目名
            String url = scheme + "://" + localhost + ":" + serverPort + contextPath + "/img/" + name;
            map.put("url", url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
    @RequestMapping("getimgs")
    public Map<String, Object> getimgs(HttpSession session,HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        //1. 拿到所有 在图片空间展示的图片
        String realPath = session.getServletContext().getRealPath("/img");
        File file = new File(realPath);
        //里面可能不止一张所以是数组
        String[] imgs = file.list();
        //创建一个新的list集合用来存储
        List<Object> list = new ArrayList<>();
        //遍历数组
        for (String s : imgs) {
            //new出一个Map集合存储配置(模拟KV配置)
            Map<String, Object> hashMap = new HashMap<>();
            //"is_dir": false,
            hashMap.put("is_dir", false);
            //"has_file": false,
            hashMap.put("has_file", false);
            //获取文件大小
            File file1 = new File(realPath, s);
            long length = file1.length();
            //"filesize": xxxx,
            hashMap.put("filesize", length);
            //"dir_path": "",
            hashMap.put("dir_path","");
            //"is_photo": true,
            hashMap.put("is_photo",true);
            //返回图片后缀名  jpg  png...
            String extension = FilenameUtils.getExtension(s);
            //"filetype": "jpg",
            hashMap.put("filetype",extension);
            //"filename": "xxx.jpg",
            //文件名
            hashMap.put("filename",s);
            String str = s.split("_")[0];
            System.out.println(s+"++");

            Long aLong = Long.valueOf(str);
            Date date = new Date(aLong);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String format = simpleDateFormat.format(date);
            hashMap.put("datetime",format);
            list.add(hashMap);
        }
        map.put("file_list",list);
        map.put("moveup_dir_path","");
        map.put("current_dir_path","");

        try {
            String scheme = request.getScheme();
            InetAddress localHost = InetAddress.getLocalHost();
            //PC-20190718ZLAM  /  192.168.1.156
            String lh = localHost.toString().split("/")[1];
            int serverPort = request.getServerPort();
            //    /cmfz
            String contextPath = request.getContextPath();
            String current_url = scheme+"://"+lh+":"+serverPort+contextPath+"/img/";
            map.put("current_url",current_url);
            map.put("total_count",imgs.length);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return map;
    }

    @ResponseBody
    @RequestMapping("addtext")
    public void addtext(Article article){
        articleService.insert(article);
        System.out.println(article+"********");
    }

    @ResponseBody
    @RequestMapping("/querybyid")
    public Article querybyid(String id,HttpSession session){
        Article querybyid = articleService.querybyid(id);
        session.setAttribute("text",querybyid);
        return querybyid;
    }
    @RequestMapping("/update")
    public void update(Article article,String content2){
        System.out.println(article+"~~~~~~");
        article.setContent(content2);
        articleService.update(article);
    }
}
