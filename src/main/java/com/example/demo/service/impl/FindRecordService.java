package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.RecordMapper;
import com.example.demo.service.IFindRecordService;
import org.springframework.stereotype.Service;

import java.util.Map;
@Service
public class FindRecordService extends ServiceImpl<RecordMapper, Map<String,String>> implements IFindRecordService {
    @Override
    public int isPush(String url) {
        return this.baseMapper.isPush(url);
    }

    @Override
    public void addRecord(String url) {
        this.baseMapper.addRecord(url);
    }
}
