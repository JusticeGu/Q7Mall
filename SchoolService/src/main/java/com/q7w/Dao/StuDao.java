package com.q7w.Dao;

import com.q7w.Entity.Exam;
import com.q7w.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author xiaogu
 * @date 2021/8/9 14:55
 **/
public interface StuDao extends JpaRepository<Student,Long> {
    List<Student> findAllByName(String name);
    List<Student> findAllByNumber(String num);
    Student findByWxopenid(String openid);
}
