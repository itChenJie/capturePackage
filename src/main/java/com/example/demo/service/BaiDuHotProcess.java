package com.example.demo.service;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 说明：数据处理
 */
public class BaiDuHotProcess {

    /**
     * 说明：抓起章节
     * @param doc
     * @return
     */
    public ArrayList<Map<String,String>> processBaiduHot(Document doc){
        ArrayList<Map<String,String>> arrayList = new ArrayList<Map<String, String>>();
        //当前页面节点
        Node divsBig = doc.childNode(1);
        //获取到章节名称列表节点
        Node theme = divsBig.childNode(3).childNode(1).childNode(19).childNode(1).childNode(1);
        //获取每一个章节名称
        for(int i=0;i<theme.childNodes().size();i++){
            Map<String,String> map = new HashMap<String, String>();
            if(i%2!=0 ){
                map.put("textName",theme.childNode(i).toString());
                arrayList.add(map);
            }
        }
        return arrayList;
    }

    /**
     * 说明：抓取文章内容
     */
    public Map<String,StringBuilder>  processText(Document doc){
        Map<String,StringBuilder> content = new HashMap<String, StringBuilder>();
        StringBuilder textStr = new StringBuilder();
        StringBuilder textName = new StringBuilder();
        Elements divsBig = doc.getElementsByClass("content_read");
        Elements divsBig2 = divsBig.get(0).getElementsByClass("box_con");
        //章节名称
        textName.append(divsBig2.get(0).getElementsByTag("h1").get(0).ownText());
        content.put("textName",textName);
        //章节内容
        textStr.append(divsBig2.get(0).getElementsByTag("div").get(6).ownText());
        content.put("textContent",textStr);
        return content;
    }

}
