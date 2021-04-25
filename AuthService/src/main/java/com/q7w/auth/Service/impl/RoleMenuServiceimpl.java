package com.q7w.auth.Service.impl;

import com.q7w.auth.DAO.RoleMenuDao;
import com.q7w.auth.Entity.RoleMenu;
import com.q7w.auth.Service.RoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedHashMap;
import java.util.List;

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
