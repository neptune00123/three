package com.baizhi.controller;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("Admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @ResponseBody
    @RequestMapping("login")
    public String login(Admin admin, HttpSession session,String enCode){
        System.out.println(enCode+"-------");
        System.out.println(admin+"-------");

        String code1 = (String) session.getAttribute("code");
        Admin admin1 = adminService.querybyusername(admin.getUsername());
        System.out.println(admin1+"++++++");
        if (!enCode.equals(code1)){
            return "验证码有误";
        }
        if (admin1==null){
            return "该用户不存在";
        }
        if (!admin.getUsername().equals(admin1.getUsername())){
            return "用户名或密码错误";
        }
        if (!admin.getPassword().equals(admin1.getPassword())){
            return "用户名或密码错误";
        }

        session.setAttribute("user",admin1);
        return "ok";
    }
}
