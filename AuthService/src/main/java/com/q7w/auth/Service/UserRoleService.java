package com.q7w.auth.Service;

import com.q7w.auth.Entity.Role;
import com.q7w.auth.Entity.UserRole;

import java.util.List;

/**
 * @author xiaogu
 * @date 2021/4/24 15:28
 **/

public interface UserRoleService {
    public List<UserRole> listAllByUid(Long uid);
    public void saveRoleChanges(Long uid, List<Role> roles);
}
