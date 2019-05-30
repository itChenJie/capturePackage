package com.example.demo.service;
import org.jsoup.Jsoup;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.jsoup.nodes.Document;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class HttpClient {
    private static final String url = "http://www.biqu6.com/19_19126/";//小说网站

    private String textUrl="http://www.biqu6.com/";//网站前缀

    private static final HttpMethod method = HttpMethod.GET;

    //抓包和数据处理
    public  List<Map<String,StringBuilder>> client(){
        //存放所有章节
        ArrayList<Map<String,String>> abh = new ArrayList<Map<String,String>>();
        //存放当前更新章节和章节内容
        List<Map<String,StringBuilder>> textStr = new ArrayList<Map<String, StringBuilder>>();
        String jieguo="";//章节url
        try {
            Document doc = Jsoup.connect(url).get();//访问网站
            //获取到所有的章节链接
            abh = new BaiDuHotProcess().processBaiduHot(doc);//执行抓包
            //截取有效的url
            for(int i=abh.size()-1;i<abh.size();i++){
                String str = abh.get(i).get("textName");
                jieguo = str.substring(str.indexOf("/")+1,str.indexOf("l")+1);//截取有效的url
                Document docText = Jsoup.connect(textUrl+jieguo).get();//章节内容页面
                textStr.add(new BaiDuHotProcess().processText(docText));//抓取章节内容
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return textStr;
    }


}
