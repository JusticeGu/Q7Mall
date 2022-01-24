package com.q7w.DAO;

import com.q7w.Entity.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author xiaogu
 * @date 2021/4/7 13:46
 **/
public interface ResourceDao extends JpaRepository<Resource,Long> {
    List<Resource> findAllByType(int type);
    Resource findByIdAndType(Long id,int type);
   // Page<Resource> findAllByType(Pageable pageable,int type);
}
