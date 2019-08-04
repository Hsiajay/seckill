package com.hsiait.hsiaer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description:
 * @Author: xiajie
 * @Date: 2019/8/4 12:13
 **/
@SpringBootApplication
@MapperScan("com.hsiait.hsiaer.usermanage.mapper")
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class,args);
    }
}
