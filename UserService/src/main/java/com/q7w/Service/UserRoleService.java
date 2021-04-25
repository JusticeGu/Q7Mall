package com.q7w.Service;

import com.q7w.DAO.UserRoleDao;
import com.q7w.Entity.Role;
import com.q7w.Entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xiaogu
 * @date 2021/4/24 15:28
 **/

public interface UserRoleService {
    public List<UserRole> listAllByUid(Long uid);
    public void saveRoleChanges(Long uid, List<Role> roles);
}
