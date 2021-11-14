package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description
 * @Author ChenWenJie
 * @Data 2021/11/13 6:34 下午
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Record {
    private Long id;
    private Long urlId;
    private String textName;
    private Date continuedTime;
    private Integer num;
}
