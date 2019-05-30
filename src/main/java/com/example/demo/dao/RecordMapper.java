package com.example.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * 说明：获取推送记录
 */
/*@Mapper*/
public interface RecordMapper extends BaseMapper<Map<String,String>> {
    //获取是当前url是否推送
    public int isPush(String url);

    //添加推送记录

    public void addRecord(String url);
}
