package com.example.demo.code;

import com.example.demo.entity.Record;
import com.example.demo.util.HttpClient;
import com.example.demo.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MailServiceImp implements MailService {
    @Autowired
    private JavaMailSenderImpl mailSender;
    @Value("${spring.mail.username}")
    private String mailName;

   @Autowired
   private RecordService recordService;
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

    /**
     * 启动抓包并且发送邮件
     */
    @Override
    public void beginCapturePackage(){
        MailService mailService = new MailServiceImp();
        HttpClient httpClient = new HttpClient();
        //执行伏天氏抓包
        List<Map<String,StringBuilder>> abh = httpClient.client();
        //发送邮箱
        for (Map<String,StringBuilder> map :abh ){
            if(isRecord(map.get("textName").toString())) {
                //执行邮箱发送功能
                this.sendSimpleMail(mailName, map);
                //添加推送记录
                recordService.save(new Record(null,map.get("textName").toString(),map.get("textContent").toString()));
            }
        }
    }

    /**
     * 判断是否已推送
     * @param textName
     * @return
     */
    public boolean isRecord(String textName){
        Record record = recordService.findOneByCode(textName);
        return record ==null?true:false;
    }
}
