package com.q7w.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author xiaogu
 * @date 2021/8/9 13:49
 **/
@Data
@Entity
public class Exam extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;//考试id
    private Long uid;//考试负责人
    private String name;//考试名称
    private byte type;//考试类型 1-班级课程单测 2-多科目统一测试
    private byte  status;//考试状态 0-初始化 1-未登分 2-分数处理中 3-登分未提交 4-提交待审核 5-成绩已发布
}
