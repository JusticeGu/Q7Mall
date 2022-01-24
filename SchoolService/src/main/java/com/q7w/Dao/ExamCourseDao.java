package com.q7w.Dao;

import com.q7w.Entity.ExamCourse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author xiaogu
 * @date 2021/8/9 15:13
 **/
public interface ExamCourseDao extends JpaRepository<ExamCourse,Long> {
    List<ExamCourse> findAllByCourse(Long course);
    List<ExamCourse> findAllByExam(Long exam);
    List<ExamCourse> findAllByExamAndCourse(Long exam,Long course);
    Boolean existsAllByCourseAndExamAndSubmit(Long course,Long exam,Boolean submit);


}
