package com.q7w.DAO;

import com.q7w.Entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author xiaogu
 * @date 2021/4/7 13:46
 **/
public interface ResourceDao extends JpaRepository<Resource,Long> {

}
