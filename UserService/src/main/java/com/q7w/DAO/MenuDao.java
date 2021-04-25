package com.q7w.DAO;

import com.q7w.Entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author xiaogu
 * @date 2021/4/23 23:38
 **/
public interface MenuDao extends JpaRepository<Menu,Long> {
    List<Menu> findAllByParentId(Long pid);
   // Menu findById(Long id);

}
