package com.q7w.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author xiaogu
 * @date 2021/8/9 14:22
 **/
@Data
@Entity
public class ClazzCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;//id
    private Long classid;// 班级id
    private Long course;//课程id
    private Long teacher;//班主任/授课教师
    private byte scoresub;//班级课程成绩提交状态
}
