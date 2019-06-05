package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @ClassName Record
 * @Description TODO
 * @Author 陈杰
 * @Date 2019/6/5 000515:10
 * @Version 1.0
 **/
@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="record")
public class Record implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "textName",length =100 ,nullable=true)
    private String textName;

    @Column(name = "context",length =5000, nullable=true)
    private String context;
}
