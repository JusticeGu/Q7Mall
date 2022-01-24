package com.q7w.Dao;

import com.q7w.Entity.ClazzCourse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author xiaogu
 * @date 2021/8/9 15:12
 **/
public interface ClassCourseDao extends JpaRepository<ClazzCourse,Long> {
    List<ClazzCourse> findAllByCourse(Long course);
    List<ClazzCourse> findAllByClassid(Long classid);
    List<ClazzCourse> findAllByCourseAndClassid(Long course, Long cid);
    List<ClazzCourse> findAllByCourseAndTeacher(Long course,Long teacher);
    void deleteAllByClassid(Long cid);
    Void deleteAllByClassidAndCourse(Long classid,Long course);
}
