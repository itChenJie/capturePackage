package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/*@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})*//*
@SpringBootApplication(exclude = WebAutoConfiguration.class)*/

@SpringBootApplication
@EnableScheduling
@MapperScan("com.example.demo.dao")
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}

