package com.q7w.auth.DAO;

import com.q7w.auth.Entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author xiaogu
 * @date 2021/4/7 13:46
 **/
public interface ResourceDao extends JpaRepository<Resource,Long> {

}
