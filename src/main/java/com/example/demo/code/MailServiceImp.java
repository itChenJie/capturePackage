package com.example.demo.code;

import com.example.demo.dao.CaptureUrlMapper;
import com.example.demo.dao.RecordMapper;
import com.example.demo.entity.CaptureUrl;
import com.example.demo.service.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

@Service
public class MailServiceImp implements MailService {
    @Autowired
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String mailName;

    @Autowired
    private RecordMapper recordMapper;
    @Autowired
    private CaptureUrlMapper captureUrlMapper;
    @Autowired
    private ValueOperations<String, String> valueOperations;

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
        HttpClient httpClient = new HttpClient();
        List<CaptureUrl> urlList = captureUrlMapper.selectList(null);
        for (CaptureUrl captureUrl : urlList) {
            List<Map<String,StringBuilder>> abh = httpClient.client(captureUrl);//执行抓包
            //发送邮箱
            for (Map<String,StringBuilder> map :abh ){
                String textContent = valueOperations.get(map.get("textName").toString() + captureUrl.getId());
                if(StringUtils.isEmpty(textContent) || !map.get("textContent").toString().equals(textContent)) {
                    this.sendSimpleMail(mailName, map);//执行邮箱发送功能
                    valueOperations.set(map.get("textName").toString() + captureUrl.getId(),map.get("textContent").toString());
                    if (isRecord(map.get("textName").toString(),captureUrl.getId()))
                        recordMapper.addRecord(map.get("textName").toString(),captureUrl.getId());//添加推送记录
                }
            }
        }

    }
    //判断是否已推送
    public boolean isRecord(String textName,Long urlId){
        return recordMapper.isPush(textName,urlId)==0?true:false;
    }
}
