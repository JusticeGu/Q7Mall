package com.q7w.Service.impl;

import com.q7w.Dao.ClassCourseDao;
import com.q7w.Dao.ClassDao;
import com.q7w.Dao.CourseDao;
import com.q7w.Dao.StuClazzDao;
import com.q7w.Entity.ClazzCourse;
import com.q7w.Entity.ClszzStu;

import com.q7w.Entity.Course;
import com.q7w.Entity.StudentClass;
import com.q7w.Service.StuClassService;
import com.q7w.Service.StuService;
import com.q7w.Service.UserFeign;
import com.q7w.Vo.ClszzStuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xiaogu
 * @date 2021/8/10 01:53
 **/
@Service
public class StuClassSerimpl implements StuClassService {
    @Autowired
    StuClazzDao stuClazzDao;
    @Autowired
    ClassCourseDao classCourseDao;
    @Autowired
    ClassDao classDao;
    @Autowired
    CourseDao courseDao;
    @Autowired
    StuService stuService;
    @Autowired
    UserFeign userFeign;
    @Override
    public int add(StudentClass stuclass) {
        stuclass.setNum(0);
        stuclass.setStatus((byte) 1);
        classDao.save(stuclass);
        return 1;
    }
    public boolean exist(Long cid,Long uid){
        List list = stuClazzDao.findAllByCidAndUid(cid, uid);
        if (list.isEmpty()){
            return false;
        }else {
            return true;
        }
    }
    @Override
    public int modefy(StudentClass stuclass) {
        classDao.save(stuclass);
        return 1;
    }

    @Override
    public int del(StudentClass stuclass) {
        return 0;
    }

    @Override
    public List liatall() {
        return classDao.findAll();
    }

    @Override
    public List<ClszzStuVO> listallstubyclass(Long stuclass) {
        List<ClszzStu> list = stuClazzDao.findAllByCid(stuclass);
        List<ClszzStuVO> stulist = new ArrayList<>();
        list.forEach(stu ->{
            ClszzStuVO clszzStuVO = new ClszzStuVO();
            clszzStuVO.setId(stu.getId());
            clszzStuVO.setCid(stu.getCid());
            clszzStuVO.setUid(stu.getUid());
            clszzStuVO.setName(stuService.getstuname(stu.getUid()));
            stulist.add(clszzStuVO);
        });
        return stulist;
    }

    @Override
    public int assocourse(Long stuclass, Long course) {
        ClazzCourse clazzCourse = new ClazzCourse();
        clazzCourse.setCourse(course);
        clazzCourse.setClassid(stuclass);
        classCourseDao.save(clazzCourse);
        return 1;
    }

    @Override
    public int resetcourse(Long stuclass) {
        classCourseDao.deleteAllByClassid(stuclass);
        return 1;
    }
//    查询班级所有课程
    @Override
    public List<Course> listcluzzcourse(Long cid) {
        List<ClazzCourse> clazzcourse = classCourseDao.findAllByClassid(cid);
        List<Course> course = new ArrayList();
        clazzcourse.forEach(cc -> {
            Course dbcourse = courseDao.findById(cc.getCourse()).get();
            course.add(dbcourse);
        });
        return course;
    }

    @Override
    public List<ClazzCourse> listcoursecluzz(Long course) {
        List<ClazzCourse> clazzcourse = classCourseDao.findAllByCourse(course);
        return clazzcourse;
    }

    /*学生入班*/
    @Override
    public int addstu(Long stuclass, List<Long> stus) {
        AtomicInteger num = new AtomicInteger();
        stus.forEach(stu ->{
            if(exist(stuclass,stu)){
            }
            else {
                ClszzStu sc = new ClszzStu();
                sc.setCid(stuclass);
                sc.setUid(stu);
                stuClazzDao.save(sc);
                num.addAndGet(1);
            }
        });
        return num.get();
    }

    @Override
    public int setteacher(Long id,Long course, Long uid) {
        List<ClazzCourse> clazzCourses = classCourseDao.findAllByCourseAndClassid(course,id);
        ClazzCourse clazzCourse = clazzCourses.get(0);
        clazzCourse.setTeacher(uid);
        classCourseDao.save(clazzCourse);
        return 1;
    }

    @Override
    public Set<Long> getteacher(Long course) {
        Set<Long> teachers = new HashSet<>();
        List<ClazzCourse> classcours =classCourseDao.findAllByCourse(course);
        classcours.forEach(cc->{
            teachers.add(cc.getTeacher());
        });
        return teachers;
    }

    @Override
    public List<ClazzCourse> listclassbycoursetea(Long course) {
        Long teacher = (Long)userFeign.getcurrentuid();
        return classCourseDao.findAllByCourseAndTeacher(course,teacher);
    }
}
