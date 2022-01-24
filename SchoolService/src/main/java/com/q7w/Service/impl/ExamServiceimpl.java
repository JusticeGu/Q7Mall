package com.q7w.Service.impl;

import com.q7w.Dao.ClassCourseDao;
import com.q7w.Dao.ExamCourseDao;
import com.q7w.Dao.ExamDao;
import com.q7w.Entity.ClazzCourse;
import com.q7w.Entity.Exam;
import com.q7w.Entity.ExamCourse;
import com.q7w.Service.ExamService;
import com.q7w.Service.StuClassService;
import com.q7w.Service.UserFeign;
import com.q7w.common.exception.GlobalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author xiaogu
 * @date 2021/8/9 16:52
 **/
@Service
public class ExamServiceimpl implements ExamService {
    @Autowired
    ExamDao examDao;
    @Autowired
    ExamCourseDao examCourseDao;
    @Autowired
    StuClassService stuClassService;
    @Autowired
    ClassCourseDao classCourseDao;
    @Autowired
    UserFeign userFeign;

    @Override
    public int add(Exam exam) {
        examDao.save(exam);
        return 1;
    }
    private boolean existexamcourse(ExamCourse examCourse){
        List list = examCourseDao.findAllByExamAndCourse(examCourse.getExam(),examCourse.getCourse());
        if (list.size()!=0){
            throw new GlobalException("1015-01","该课程在本场考试已存在配置信息");
        }else {
            return false;
        }
    }
    @Override
    public int asocourse(ExamCourse examCourse) {
            existexamcourse(examCourse);
            ExamCourse newexamCourse = new ExamCourse();
            try {
                newexamCourse.setPercent(examCourse.getPercent());
                newexamCourse.setNorpercent(examCourse.getNorpercent());
                newexamCourse.setExam(examCourse.getExam());
                newexamCourse.setCourse(examCourse.getCourse());
                newexamCourse.setSubmit(false);
                examCourseDao.save(newexamCourse);
            }catch (Exception e){
            }

        return 1;
    }

    @Override
    public int setexamcourse(ExamCourse examCourse) {
        ExamCourse examCoursedb = examCourseDao.findById(examCourse.getId()).get();
        examCoursedb.setNorpercent(examCourse.getNorpercent());
        examCoursedb.setPercent(examCoursedb.getPercent());
        examCoursedb.setSubmit(false);
        examCourseDao.save(examCoursedb);
        return 1;
    }

    @Override
    public int delexamcourse(Long id) {
        examCourseDao.deleteById(id);
        return 1;
    }

    @Override
    public List<ExamCourse> getexamcourse(Long eid) {
        return examCourseDao.findAllByExam(eid);
    }

    @Override
    public List getoscorestulist(Long courseid, Long course) {
        return null;
    }

    @Override
    public int modefy(Exam exam) {
        return 0;
    }

    @Override
    public int del(Exam exam) {
        examDao.deleteById(exam.getId());
        return 1;
    }

    @Override
    public List liatall() {
        return examDao.findAll();
    }

    @Override
    public ExamCourse getinfobyeid(Long id) {
        try{
            ExamCourse examCourse = examCourseDao.findById(id).get();
            return examCourse;
        }catch (Exception e)
        {
            throw new GlobalException("1005X01","考试记录不存在");
        }

    }

    @Override
    public ExamCourse getinfobyexamcourse(Long exam, Long course) {
        try{
            ExamCourse examCourse = examCourseDao.findAllByExamAndCourse(exam,course).get(0);
            return examCourse;
        }catch (Exception e)
        {
            throw new GlobalException("1005X01","考试记录不存在");
        }

    }

    @Override
    public List<ExamCourse> getinfobyexam(Long id) {
        try{

            List<ExamCourse> examCourse = examCourseDao.findAllByExam(id);
            return examCourse;
        }catch (Exception e)
        {
            throw new GlobalException("1005X01","考试记录不存在");
        }
    }

    @Override
    public int publish(Long id) {
        AtomicBoolean status= new AtomicBoolean(false);
        List<ExamCourse> allexamcourses = examCourseDao.findAllByExam(id);
        Exam exam = examDao.findById(id).get();
        if (exam.getUid()!=(Long)userFeign.getcurrentuid()){
            throw new GlobalException("403-1001","您没有发布该考试成绩的权限");
        }
        allexamcourses.forEach(ac -> {
            status.set(ac.isSubmit());
        });
        if (status.get() ==true){
            Exam examdb = examDao.findById(id).get();
            examdb.setStatus((byte)5);
            examDao.save(examdb);
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public Set<Long> getexamcoursescs(Long exam, Long course) {
        List<ExamCourse> examCourses = examCourseDao.findAllByExamAndCourse(exam, course);
        Long teacher = (Long)userFeign.getcurrentuid();
        Set<Long> clazzids = new HashSet<Long>();
        examCourses.forEach(examCourse -> {
            List<ClazzCourse> clazzCourses = classCourseDao.findAllByCourseAndTeacher(examCourse.getCourse(),teacher);
            clazzCourses.forEach(clazzCourse -> {
                clazzids.add(clazzCourse.getClassid());
            });
        });
        return clazzids;
    }

    @Override
    public int scoresubmit(Long id) {
        ExamCourse examCoursedb = examCourseDao.findById(id).get();
        if (!(stuClassService.getteacher(examCoursedb.getCourse())
                .contains((Long)userFeign.getcurrentuid())))
        {
            throw new GlobalException("403-1001","您没有该考试提交权限，请联系负责人");
        }
        examCoursedb.setSubmit(true);
        return 1;
    }

    @Override
    public boolean examstatus(Long id) {
        if(examDao.findById(id).get().getStatus()==5){return true;}
        else {
            return false;
        }
    }

    @Override
    public Long manageuid(Long exam) {
        return examDao.findById(exam).get().getUid();
    }
}
