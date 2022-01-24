package com.q7w.DAO;


import com.q7w.Entity.Userpro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author xiaogu
 * @date 2021/4/29 13:23
 **/
public interface UserProDao extends JpaRepository<Userpro,Long> {
    Userpro findByUid(Long uid);
    List<Userpro> findAllByRealnameLike(String name);

}
