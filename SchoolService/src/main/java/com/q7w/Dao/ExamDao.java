package com.q7w.Dao;

import com.q7w.Entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author xiaogu
 * @date 2021/8/9 14:55
 **/
public interface ExamDao extends JpaRepository<Exam,Long> {
    List<Exam> findAllByName(String name);
}
