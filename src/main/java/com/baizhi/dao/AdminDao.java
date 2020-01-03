package com.baizhi.dao;

import com.baizhi.entity.Admin;



public interface AdminDao {
/**
 * 根据后台管理员用户名查信息
 * */
    Admin querybyusername(String username);

}
