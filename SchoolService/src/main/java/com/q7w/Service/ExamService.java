package com.q7w.Service;

import com.q7w.Entity.Exam;
import com.q7w.Entity.ExamCourse;

import java.util.List;
import java.util.Set;

/**
 * @author xiaogu
 * @date 2021/8/9 15:15
 **/
public interface ExamService {
    public int add(Exam exam);
    public int asocourse(ExamCourse examCourse);//关联课程
    public int setexamcourse(ExamCourse examCourse);
    public List<ExamCourse> getexamcourse(Long eid);//获取考试关联课程
    public Set<Long> getexamcoursescs(Long exam, Long course);//获取选定考试、科目负责班级
    public List<ExamCourse> getinfobyexam(Long id);
    public ExamCourse getinfobyeid(Long id);
    public ExamCourse getinfobyexamcourse(Long exam,Long course);
    public int delexamcourse(Long id);
    public int modefy(Exam exam);
    public int del(Exam exam);
    public List liatall();
    public List getoscorestulist(Long courseid,Long course);
    public int publish(Long exam);//发布成绩
    public int scoresubmit(Long id);//成绩提交
    public boolean examstatus(Long id);
    public Long manageuid(Long exam);
}
