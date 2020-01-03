package com.baizhi.dao;

import com.baizhi.entity.User;

import java.util.List;

public interface UserDao {
    //查询所有用户
    List<User> queryall();
    //根据手机号查询用户
    User querybynumber(String phone_number);
    //根据id查询上师id
    User queryid(String id);
}
