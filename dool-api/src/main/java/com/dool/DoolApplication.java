package com.dool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = {"com.dool.biz.dao.mapper"})
public class DoolApplication {

    public static void main(String[] args) {
        SpringApplication.run(DoolApplication.class, args);
    }
}
