package com.q7w.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author xiaogu
 * @date 2021/8/9 14:34
 **/
@Data
@Entity
public class ExamCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;//id
    private Long exam;//考试id
    private Long course;//课程id
    private int percent;//课程分数权值
    private int norpercent;//平时成绩占比
    private boolean submit;//成绩提交状态
 }
