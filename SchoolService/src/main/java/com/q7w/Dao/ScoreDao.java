package com.q7w.Dao;

import com.q7w.Entity.Exam;
import com.q7w.Entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author xiaogu
 * @date 2021/8/9 14:55
 **/
public interface ScoreDao extends JpaRepository<Score,Long> {
    List<Score> findAllByUid(Long uid);
    List<Score> findAllByExam(Long exam);
    List<Score> findAllByExamAndUid(Long exam,Long uid);
    List<Score> findAllByCourse(Long course);
    List<Score> findAllByCourseAndExam(Long course,Long exam);
    List<Score> findAllByCourseAndExamAndCid(Long course,Long exam,Long cid);
    List<Score> findAllByCourseAndExamAndUid(Long course,Long exam,Long uid);
    Score findByCourseAndExamAndUid(Long course,Long exam,Long uid);
    List<Score> findAllByExamAndCourseAndStatus(Long exam,Long course,int status);

}
