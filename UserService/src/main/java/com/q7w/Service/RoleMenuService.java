package com.q7w.Service;

import com.q7w.Entity.RoleMenu;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author xiaogu
 * @date 2021/4/24 15:37
 **/
public interface RoleMenuService {
    public List<RoleMenu> findAllByRid(Long rid);
    public void deleteAllByRid(Long rid);
    public void save(RoleMenu rm);
    public boolean updateRoleMenu(Long rid, LinkedHashMap menusIds);
    public List<Long> getrolesbymenu(Long mid);
}
