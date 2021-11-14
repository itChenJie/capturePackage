package com.example.demo.code;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 说明：抓取每天更新的文章
 */
@Component
@Log
public class Capture {
    @Autowired
    private MailService mailService;

    @Scheduled(cron="0 0/10 * * * ?")//定时任务 每15分钟执行一次
    public void morning(){
        log.info(String.valueOf(new Date()));
        mailService.beginCapturePackage();//启动抓包
    }
    /*//晚上
    @Scheduled(cron = "0 30 23 * * ?")//定时任务
    public void evening(){
        mailService.beginCapturePackage();//启动抓包
    }*/
}
