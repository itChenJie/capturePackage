package com.example.demo.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfiguration {

    @Bean
    public JavaMailSenderImpl JavaMailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.qq.com");
        mailSender.setUsername("1690147900@qq.com");
        mailSender.setPassword("btjzabiuabuufbgg");
        mailSender.setPort(587);
        mailSender.setDefaultEncoding("UTF-8");
        return  mailSender;
    }
}