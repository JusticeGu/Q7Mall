package com.q7w.Service.impl;

import com.q7w.DAO.RoleDao;
import com.q7w.Entity.*;
import com.q7w.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author xiaogu
 * @date 2021/4/6 21:47
 **/
@Service
public class RoleServiceimpl implements RoleService {
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private ResourceService resourceService;
    @Autowired
    MenuService menuService;
    @Autowired
    UserService userService;
    @Autowired
    UserRoleService userRoleService;
    @Autowired
    RoleMenuService roleMenuService;
    @Autowired
    RoleResourceService roleResourceService;
    @Override
    public int create(Role role) {
        role.setCreateTime(new Date().getTime());
        role.setAdminCount(0);
        roleDao.save(role);
        return 1;
    }

    @Override
    public int update(Long id, Role role) {
        role.setId(id);
        roleDao.save(role);
        return 1;
    }

    @Override
    public int delete(List<Long> ids) {
        for (Long id :ids){
        roleDao.deleteById(id);
        }
        resourceService.initResourceRolesMap();
        return 1;
    }

    @Override
    public List<Role> list() {
        List<Role> roles = roleDao.findAll();
        List<Resource> resources;
        List<Menu> menus;
        for (Role role : roles) {
            resources = resourceService.listPermsByRoleId(role.getId());
            menus = menuService.getMenusByRoleId(role.getId());
            role.setResources(resources);
            role.setMenus(menus);
        }
        return roles;
    }
    @Override
    public Role findById(Long id) {
        return roleDao.findById(id).get();
    }
    @Override
    public List<Role> list(String keyword, Integer pageSize, Integer pageNum) {

        return roleDao.findAll();
    }

    @Override
    public List<Role> listRolesByUser(Long uid) {
        List<Role> roles = new ArrayList<>();
        List<UserRole> urs = userRoleService.listAllByUid(uid);
        urs.forEach(ur -> roles.add(roleDao.findById(ur.getRid()).get()));
        return roles;
    }

    @Override
    public List<Menu> listroleMenu(Long roleId) {
        return menuService.getMenusByRoleId(roleId);
    }

    @Override
    public List<Resource> listroleResource(Long roleId) {
        return resourceService.listPermsByRoleId(roleId);
    }

    @Override
    public int allocMenu(Long roleId, List<Long> menuIds) {
        roleMenuService.deleteAllByRid(roleId);
      //  批量插入新关系
        for (Long menuId : menuIds) {
            RoleMenu rolemenu = new RoleMenu();
            rolemenu.setRid(roleId);
            rolemenu.setMid(menuId);
            roleMenuService.save(rolemenu);
        }
        return menuIds.size();
    }

    @Override
    public int allocResource(Long roleId, List<Long> resourceIds) {
        roleResourceService.savePermChanges(roleId,resourceIds);
        return 1;
    }

    @Override
    public int allocuserrole(Long uid, List<Long> rids) {
        userRoleService.saveRoleChanges(uid,rids);
        return 1;
    }
}
