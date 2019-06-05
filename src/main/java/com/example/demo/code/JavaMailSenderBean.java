package com.example.demo.code;

import com.example.demo.entity.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * @ClassName JavaMailSenderBean
 * @Description TODO
 * @Author 陈杰
 * @Date 2019/6/5 000515:25
 * @Version 1.0
 **/
@Configuration
public class JavaMailSenderBean {

    @Autowired
    private Mail mail;

    @Bean
    public JavaMailSenderImpl JavaMailSender(){

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(mail.getHost());
        mailSender.setUsername(mail.getUsername());
        mailSender.setPassword(mail.getPassword());
        return  mailSender;
    }
}
