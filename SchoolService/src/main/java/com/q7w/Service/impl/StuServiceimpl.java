package com.q7w.Service.impl;

import com.q7w.Dao.StuDao;
import com.q7w.Entity.Student;
import com.q7w.Service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xiaogu
 * @date 2021/8/10 01:53
 **/
@Service
public class StuServiceimpl implements StuService {
    @Autowired
    StuDao stuDao;
    @Override
    public int add(Student student) {
        stuDao.save(student);
        return 1;
    }

    @Override
    public int modefy(Student student) {
        stuDao.save(student);
        return 1;
    }

    @Override
    public int del(Student student) {
        stuDao.deleteById(student.getId());
        return 0;
    }

    @Override
    public List liatall() {
        return stuDao.findAll();
    }

    @Override
    public String getstuname(Long uid) {
        return stuDao.findById(uid).get().getName();
    }

}
