package com.q7w.auth.DAO;

import com.q7w.auth.Entity.RoleResource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author xiaogu
 * @date 2021/4/7 20:02
 **/
public interface RoleResourceDao extends JpaRepository<RoleResource,Long> {
    List<RoleResource> findAllByRid(Long rid);
    void  deleteAllByRid(Long rid);
}
