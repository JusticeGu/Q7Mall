package com.q7w.Service;

import com.q7w.Entity.Course;

import java.util.List;

/**
 * @author xiaogu
 * @date 2021/8/9 15:14
 **/
public interface CourseService {
    public int add(Course course);
    public int modefy(Course course);
    public int del(Course course);
    public List liatall();
    public String getnamebyid(Long id);
}
