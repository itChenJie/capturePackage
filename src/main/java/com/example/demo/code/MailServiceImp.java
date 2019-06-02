package com.example.demo.code;

import com.example.demo.service.HttpClient;
import com.example.demo.service.IFindRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class MailServiceImp implements MailService {
    @Autowired
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String mailName;

    @Resource(name = "findRecordService")
    private IFindRecordService findRecordService;//推送记录接口

    @Override
    public void sendSimpleMail(String to, Map<String,StringBuilder> map) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(mailName);
        mailMessage.setSubject(map.get("textName").toString());
        mailMessage.setTo(to);
        mailMessage.setText(map.get("textContent").toString());
        try{
            mailSender.send(mailMessage);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    //启动抓包并且发送邮件Chen_Jie06@163
    @Override
    public void beginCapturePackage(){
        MailService mailService = new MailServiceImp();
        HttpClient httpClient = new HttpClient();
        List<Map<String,StringBuilder>> abh = httpClient.client();//执行伏天氏抓包
        //发送邮箱
        for (Map<String,StringBuilder> map :abh ){
            if(isRecord(map.get("textName").toString())) {
                this.sendSimpleMail(mailName, map);//执行邮箱发送功能
                findRecordService.addRecord(map.get("textName").toString());//添加推送记录
            }
        }

    }
    //判断是否已推送
    public boolean isRecord(String textName){
        return findRecordService.isPush(textName)==0?true:false;
    }
}
