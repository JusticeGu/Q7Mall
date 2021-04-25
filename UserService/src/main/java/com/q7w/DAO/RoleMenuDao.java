package com.q7w.DAO;

import com.q7w.Entity.RoleMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author xiaogu
 * @date 2021/4/24 15:36
 **/
public interface RoleMenuDao extends JpaRepository<RoleMenu,Long> {
    List<RoleMenu> findAllByRid(Long rid);
    void deleteAllByRid(Long rid);
}
