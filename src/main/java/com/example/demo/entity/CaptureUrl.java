package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Description
 * @Author ChenWenJie
 * @Data 2021/11/13 5:04 下午
 **/
@TableName(value = "capture_url")
@Data
public class CaptureUrl {
    private Long id;
    private String url;
    private String urlPrefix;
    private String name;
    private Integer type;
}
