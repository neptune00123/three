package com.baizhi.service;

import com.baizhi.entity.Admin;
import org.springframework.stereotype.Service;

@Service
public interface AdminService {
    Admin querybyusername(String username);
}
