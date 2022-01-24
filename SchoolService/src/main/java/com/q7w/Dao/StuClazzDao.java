package com.q7w.Dao;

import com.q7w.Entity.ClszzStu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author xiaogu
 * @date 2021/8/9 15:12
 **/
public interface StuClazzDao extends JpaRepository<ClszzStu,Long> {
    List<ClszzStu> findAllByUid(Long uid);
    List<ClszzStu> findAllByCid(Long cid);
    List<ClszzStu> findAllByCidAndUid(Long cid, Long uid);
}
