package com.q7w.DAO;


import com.q7w.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author xiaogu
 * @date 2020/11/27 14:36
 **/
public interface UserDao extends JpaRepository<User,Long> {
    User findUserByUsername(String username);
    User findUserById(Long id);
}