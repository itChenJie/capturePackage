package com.example.demo;

import com.example.demo.code.Capture;
import com.example.demo.code.MailService;
import com.example.demo.service.HttpClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    @Autowired
    private MailService mailService;
    @Test
    public void contextLoads() {
        //之间new的对象不归ioc管理 里面有的调用用到 注入的都将失效
        /*Capture capture = new Capture();
        capture.beginCapturePackage();*/
        mailService.beginCapturePackage();
    }



}

