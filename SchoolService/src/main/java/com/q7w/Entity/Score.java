package com.q7w.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author xiaogu
 * @date 2021/8/9 01:14
 **/
@Data
@Entity
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;//成绩id
    private Long uid;//学生id
    private String name;
    private Long cid;//班级id
    private Long course;//课程id
    private Long exam;//考试id
    private int score;//平时分数
    private int exscore;//考试成绩
    private int finalscore;//最终成绩
    private int status;//0-待考试 1-待登分  2-待发布 3-已发布 -1 缺考 -2 异常
    private String content;//备注
    private Byte type;//成绩类型1-正考2-补考3-重修 0-待考
}
