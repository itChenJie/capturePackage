package com.example.demo.service;

import com.example.demo.dao.RecordDao;
import com.example.demo.entity.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @ClassName RecordService
 * @Author 陈杰
 * @Date 2019/6/5 000515:16
 * @Version 1.0
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class RecordService {
    @Autowired
    private RecordDao recordDao;

    public Record findOneByCode(String code){
        Optional<Record> records=this.recordDao.findOne(Example.of(Record.builder().textName(code).build()));
        return records.orElse(null);
    }
    public Record save(Record record){
        return this.recordDao.save(record);
    }
}
