package com.baizhi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication
@MapperScan(basePackages = "com.baizhi.dao")
public class CmfzZzhApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmfzZzhApplication.class, args);
    }

}
