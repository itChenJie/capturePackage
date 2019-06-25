package com.example.demo.code;

import com.example.demo.entity.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

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
        mailSender.setPort(mail.getPort());
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        // 使用JSSE的SSL
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        // socketfactory来取代默认的socketfactory
        // 只处理SSL的连接,对于非SSL的连接不做处理
        properties.put("mail.smtp.socketFactory.fallback", "false");
        properties.put("mail.smtp.ssl.enable", true);
        mailSender.setJavaMailProperties(properties);
        return  mailSender;
    }
}
