package com.baizhi.controller;

import com.baizhi.dao.UserDao;
import com.baizhi.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("account")
public class UserController {
    @Autowired
    private UserDao userDao;
    @RequestMapping("login")
    public String queryalluser(String phone_number){
        User querybynumber = userDao.querybynumber(phone_number);
        if (querybynumber==null){
            return "用户名或密码错误";

        }
        return "";
    }


}
