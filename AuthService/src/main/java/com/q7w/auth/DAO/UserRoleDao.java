package com.q7w.auth.DAO;

import com.q7w.auth.Entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author xiaogu
 * @date 2021/4/24 15:28
 **/
public interface UserRoleDao extends JpaRepository<UserRole,Long> {
    List<UserRole> findAllByUid(Long uid);
    void deleteAllByUid(Long uid);
}
