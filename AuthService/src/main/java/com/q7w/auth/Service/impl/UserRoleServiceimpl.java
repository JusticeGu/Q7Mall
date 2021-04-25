package com.q7w.auth.Service.impl;

import com.q7w.auth.DAO.UserRoleDao;
import com.q7w.auth.Entity.Role;
import com.q7w.auth.Entity.UserRole;
import com.q7w.auth.Service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xiaogu
 * @date 2021/4/24 15:29
 **/
@Service
public class UserRoleServiceimpl implements UserRoleService {
    @Autowired
    UserRoleDao userRoleDao;

    @Override
    public List<UserRole> listAllByUid(Long uid) {
        return userRoleDao.findAllByUid(uid);
    }

    @Override
    public void saveRoleChanges(Long uid, List<Role> roles) {
        userRoleDao.deleteAllByUid(uid);
        roles.forEach(r -> {
            UserRole ur = new UserRole();
            ur.setUid(uid);
            ur.setRid(r.getId());
            userRoleDao.save(ur);
        });
    }
}
