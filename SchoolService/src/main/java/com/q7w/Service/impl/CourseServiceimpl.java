package com.q7w.Service.impl;

import com.q7w.Dao.CourseDao;
import com.q7w.Entity.Course;
import com.q7w.Service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xiaogu
 * @date 2021/8/10 01:52
 **/
@Service
public class CourseServiceimpl implements CourseService {
    @Autowired
    CourseDao courseDao;
    @Override
    public int add(Course course) {
        courseDao.save(course);
        return 1;
    }

    @Override
    public int modefy(Course course) {
        courseDao.save(course);
        return 1;
    }

    @Override
    public int del(Course course) {
        courseDao.deleteById(course.getId());
        return 1;
    }

    @Override
    public List liatall() {
        return courseDao.findAll();
    }

    @Override
    public String getnamebyid(Long id) {
        return null;
    }
}
