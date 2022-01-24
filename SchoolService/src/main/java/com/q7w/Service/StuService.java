package com.q7w.Service;

import com.q7w.Entity.Course;
import com.q7w.Entity.Student;

import java.util.List;

/**
 * @author xiaogu
 * @date 2021/8/9 15:14
 **/
public interface StuService {
    public int add(Student student);
    public int modefy(Student student);
    public int del(Student student);
    public List liatall();
    public String getstuname(Long uid);
}
