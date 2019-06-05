package com.example.demo.dao;

import com.example.demo.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *@ClassName RecordDao
 *@Description TODO
 *@Author 陈杰
 *@Date 2019/6/5 000515:15
 *@Version 1.0
**/
public interface RecordDao extends JpaRepository<Record,Long> {
}
