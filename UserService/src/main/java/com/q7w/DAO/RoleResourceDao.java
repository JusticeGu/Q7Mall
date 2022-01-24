package com.q7w.DAO;

import com.q7w.Entity.RoleResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xiaogu
 * @date 2021/4/7 20:02
 **/
public interface RoleResourceDao extends JpaRepository<RoleResource,Long> {
    List<RoleResource> findAllByRid(Long rid);
    @Query(nativeQuery = true,value = "select * from auth_role_Resource rr inner join auth_Resource p on (rr.pid =p.id) where rr.rid = ?1 and p.type = ?2")
    List<RoleResource> findAllByRidAndType(Long rid,int type);
    @Transactional
    @Modifying
    @Query(nativeQuery = true,value = "delete rr from auth_role_Resource rr inner join auth_Resource p on (rr.pid =p.id) where rr.rid = ?1 and p.type = ?2 and p.modelid = ?3")
    void delAllByRidTypeModel(Long rid,int type,Long modelid);

    void  deleteAllByRid(Long rid);
    void  deleteAllByPid(Long pid);
}
