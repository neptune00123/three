package com.baizhi.controller;

import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

@RequestMapping("banner")
@RestController
public class BannerController {
    @Autowired
    private BannerService bannerService;


    @RequestMapping("queryall")
    public Map<String, Object> queryallbanner(Integer rows, Integer page) {
        return bannerService.queryall(rows, page);
    }


    @RequestMapping("edit")
    public Map<String, Object> edit(String oper, Banner banner,String[] id) {
        Map<String, Object> map = new HashMap<>();
        if ("add".equals(oper)) {
            banner.setId(UUID.randomUUID().toString());
            banner.setCreate_date(new Date());
            banner.setTitle(banner.getTitle());
            banner.setStatus(banner.getStatus());
            banner.setImg("null");
            bannerService.insert(banner);
            System.out.println(banner + "------");
            map.put("id", banner.getId());
        } else if ("edit".equals(oper)) {
            System.out.println("+++++++++++++");
            banner.setCreate_date(new Date());
            banner.setTitle(banner.getTitle());
            banner.setStatus(banner.getStatus());
            /*if (banner.getImg() == null) {
                Banner banner1 = bannerService.querybyid(banner.getId());
                banner.setImg(banner1.getImg());
            } else {
                banner.setImg("null");
            }*/
            if (banner.getImg().equals("")){
                banner.setImg(null);
                bannerService.updateall(banner);
                map.put("msg","修改成功");
            }else{
                bannerService.updateall(banner);
                map.put("msg","修改成功");
                map.put("id",banner.getId());
            }
            bannerService.updateall(banner);
            System.out.println(banner + "*******---");
            map.put("id", banner.getId());
        }else if ("del".equals(oper)){
            bannerService.delete(id);
        }
    return map;
    }

    @RequestMapping("bannerUpload")
    public void upload(MultipartFile img, String id, HttpSession session) {

        //1. 获得 upload的路径
        String realPath = session.getServletContext().getRealPath("/img");
        //2.判断文件夹是否存在
        File file1 = new File(realPath);
        if (!file1.exists()) {
            file1.mkdirs();
        }
        //3.获取文件真实名字
        String filename = img.getOriginalFilename();
        System.out.println(filename + "------");
        //4. 为了防止同一个文件多次上传发生覆盖  拼接时间戳
        String realname = new Date().getTime() + "_" + filename;
        System.out.println(realname + "-----");
        //5.文件上传
        try {
            img.transferTo(new File(realPath, realname));
            bannerService.update(id, realname);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("motifyUpload")
    public void bannerUpload(MultipartFile img, String id, HttpSession session) {
        String realPath = session.getServletContext().getRealPath("/img");
        File file = new File(realPath);
        if(!file.exists()){
            file.mkdirs();
        }
        String realName =  img.getOriginalFilename();
        String name = new Date().getTime()+"_"+realName;
        try {
            img.transferTo(new File(realPath,name));
            Banner banner = new Banner();
            banner.setId(id);
            System.out.println(id+"****//////**---");
            banner.setImg(name);
            System.out.println(name+"-----////*/*");
            bannerService.updateall(banner);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}