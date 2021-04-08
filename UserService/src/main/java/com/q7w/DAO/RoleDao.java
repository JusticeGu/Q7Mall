package com.q7w.DAO;

import com.q7w.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author xiaogu
 * @date 2021/4/6 21:48
 **/
public interface RoleDao  extends JpaRepository<Role,Long> {
}
