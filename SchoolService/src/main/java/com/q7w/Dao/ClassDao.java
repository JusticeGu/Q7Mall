package com.q7w.Dao;

import com.q7w.Entity.StudentClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author xiaogu
 * @date 2021/8/9 14:54
 **/
public interface ClassDao extends JpaRepository<StudentClass,Long> {
    List<StudentClass> findAllByName(String name);
}
