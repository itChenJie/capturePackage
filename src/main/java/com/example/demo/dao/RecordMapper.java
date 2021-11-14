package com.example.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.example.demo.entity.Record;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 说明：获取推送记录
 */
@Mapper
public interface RecordMapper extends BaseMapper<Record> {
    //获取是当前url是否推送
    int isPush(String url,Long urlId);

    //添加推送记录
    void addRecord(String url,Long urlId);
    // 查询最近一张
    Record findRecentChapter(Long urlId);
    // 查询在轮训内的章节
    List<Record> findRotationInnerChapter(Long urlId);
}
