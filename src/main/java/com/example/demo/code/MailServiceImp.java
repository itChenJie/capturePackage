package com.example.demo.code;

import cn.hutool.core.date.DateUtil;
import com.example.demo.dao.CaptureUrlMapper;
import com.example.demo.dao.RecordMapper;
import com.example.demo.entity.CaptureUrl;
import com.example.demo.entity.Record;
import com.example.demo.service.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;

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
            // 查询出最近拉取的章节数据，并且未过轮训时间内的章节数据
            List<Record> recordList = new ArrayList<>();
            Record chapter = recordMapper.findRecentChapter(captureUrl.getId());
            empty(chapter,recordList);
            List<Record> records = recordMapper.findRotationInnerChapter(captureUrl.getId());
            empty(records,recordList);
            Optional<Record> optional = recordList.stream().sorted(Comparator.comparing(Record::getNum).reversed()).findFirst();
            List<Map<String,StringBuilder>> abh = httpClient.client(captureUrl,optional);//执行抓包
            //发送邮箱
            for (Map<String,StringBuilder> map :abh ){
                String textContent = valueOperations.get(map.get("textName").toString() + captureUrl.getId());
                if(StringUtils.isEmpty(textContent) || !map.get("textContent").toString().equals(textContent)) {
                    this.sendSimpleMail(mailName, map);//执行邮箱发送功能
                    valueOperations.set(map.get("textName").toString() + captureUrl.getId()
                            ,map.get("textContent").toString()  ,2, TimeUnit.HOURS);
                    if (isRecord(map.get("textName").toString(),captureUrl.getId())){
                        //添加推送记录
                        Record record = Record.builder().textName(map.get("textName").toString())
                                .urlId(captureUrl.getId())
                                .continuedTime(DateUtil.offsetHour(new Date(), 1).toJdkDate())
                                .num(Integer.valueOf(map.get("index").toString()))
                                .build();
                        recordMapper.insert(record);
                    }


                }
            }
        }

    }
    //判断是否已推送
    public boolean isRecord(String textName,Long urlId){
        return recordMapper.isPush(textName,urlId)==0?true:false;
    }

    private void empty(Object o,List<Record> recordList){
        if (o!=null){
            if (o instanceof AbstractList){
                recordList.addAll((Collection<? extends Record>) o);
            }else {
                recordList.add((Record) o);
            }
        }
    }
}
