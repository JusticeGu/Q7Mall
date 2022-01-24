package com.q7w.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author xiaogu
 * @date 2021/8/9 01:13
 **/
@Data
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;//课程id
    private String name;//课程名称
    private String desp;//课程介绍
    private int orginprice;//官方价格
    private byte status;//课程状态
}
