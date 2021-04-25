package com.q7w.Service.impl;

import com.q7w.DAO.RoleMenuDao;
import com.q7w.Entity.RoleMenu;
import com.q7w.Service.RoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.jpa.repository.Modifying;
import java.util.LinkedHashMap;
import java.util.List;
import javax.transaction.Transactional;
/**
 * @author xiaogu
 * @date 2021/4/24 15:37
 **/
@Service
public class RoleMenuServiceimpl implements RoleMenuService {
    @Autowired
    RoleMenuDao roleMenuDao;
    @Override
    public List<RoleMenu> findAllByRid(Long rid) {
        return roleMenuDao.findAllByRid(rid);
    }

    @Override
    @Modifying
    @Transactional
    public void deleteAllByRid(Long rid) {
        roleMenuDao.deleteAllByRid(rid);
    }

    @Override
    public void save(RoleMenu rm) {
        roleMenuDao.save(rm);
    }

    @Override
    @Modifying
    @Transactional
    public boolean updateRoleMenu(Long rid, LinkedHashMap menusIds) {
        try {
            deleteAllByRid(rid);
            for (Object value : menusIds.values()) {
                for (Long mid : (List<Long>) value) {
                    RoleMenu rm = new RoleMenu();
                    rm.setRid(rid);
                    rm.setMid(mid);
                    roleMenuDao.save(rm);
                }
            }
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }
}
