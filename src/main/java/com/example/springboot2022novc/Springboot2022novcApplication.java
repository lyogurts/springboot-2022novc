package com.example.springboot2022novc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.example.springboot2022novc.dao")
@EnableScheduling//定时启动
public class Springboot2022novcApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot2022novcApplication.class, args);
    }

}
