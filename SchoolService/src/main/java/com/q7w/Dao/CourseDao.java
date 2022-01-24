package com.q7w.Dao;

import com.q7w.Entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author xiaogu
 * @date 2021/8/9 14:54
 **/
public interface CourseDao extends JpaRepository<Course,Long> {
    List<Course> findAllByName(String name);
}
