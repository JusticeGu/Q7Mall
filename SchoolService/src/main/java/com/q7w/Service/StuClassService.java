package com.q7w.Service;



import com.q7w.Entity.ClazzCourse;
import com.q7w.Entity.Course;
import com.q7w.Entity.StudentClass;
import com.q7w.Vo.ClszzStuVO;

import java.util.List;
import java.util.Set;

/**
 * @author xiaogu
 * @date 2021/8/10 01:39
 **/
public interface StuClassService {
    public int add(StudentClass stuclass);
    public int modefy(StudentClass stuclass);
    public int del(StudentClass stuclass);
    public List liatall();
    public List<ClszzStuVO> listallstubyclass(Long stuclass);
    public List<Course> listcluzzcourse(Long cid);
    public List<ClazzCourse> listcoursecluzz(Long course);
    public List<ClazzCourse> listclassbycoursetea(Long course);
    public int assocourse(Long stuclass,Long course);
    public int resetcourse(Long stuclass);
    public int addstu(Long stuclass,List<Long> stus);
    public int setteacher(Long cid,Long course,Long uid);
    public Set<Long> getteacher(Long course);

}
