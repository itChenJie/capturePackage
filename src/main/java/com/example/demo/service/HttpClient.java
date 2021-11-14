package com.example.demo.service;
import com.example.demo.entity.CaptureUrl;
import com.example.demo.entity.Record;
import org.jsoup.Jsoup;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.jsoup.nodes.Document;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;


@Service
public class HttpClient {
    private static final String url = "http://www.biqu6.com/19_19126/";//小说网站

    private String textUrl="http://www.biqu6.com/";//网站前缀

    private static final HttpMethod method = HttpMethod.GET;

    //抓包和数据处理
    public static List<Map<String,StringBuilder>> client(CaptureUrl captureUrl, Optional<Record> record){
        //存放所有章节
        ArrayList<Map<String,String>> abh = new ArrayList<Map<String,String>>();
        //存放当前更新章节和章节内容
        List<Map<String,StringBuilder>> textStr = new ArrayList<Map<String, StringBuilder>>();
        String jieguo="";//章节url
        try {
            Document doc = Jsoup.connect(captureUrl.getUrl()).get();//访问网站
            //获取到所有的章节链接
            abh = new BaiDuHotProcess().processBaiduHot(doc);//执行抓包
            Integer index = abh.size()-1;
            if (record.isPresent()){
                // 如果这条数据已过轮训时间则获取后面的
                if (record.get().getContinuedTime().getTime()>new Date().getTime()) {
                    index = record.get().getNum();
                }else {
                    index = record.get().getNum()+1;
                }

            }
            //截取有效的url
            for(int i=index;i<abh.size();i++){
                String str = abh.get(i).get("textName");
                jieguo = str.substring(str.indexOf("href=")+6,str.indexOf("l")+1);//截取有效的url
                Document docText = Jsoup.connect(captureUrl.getUrlPrefix()+jieguo).get();//章节内容页面
                Map<String, StringBuilder> text = new BaiDuHotProcess().processText(docText);
                text.put("index",new StringBuilder().append(i));
                textStr.add(text);//抓取章节内容
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return textStr;
    }
}
