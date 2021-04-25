package com.q7w.Service.impl;

import com.q7w.DAO.RoleDao;
import com.q7w.Entity.Menu;
import com.q7w.Entity.Resource;
import com.q7w.Entity.Role;
import com.q7w.Entity.UserRole;
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
    public List<Role> listRolesByUser(String username) {
        Long uid =  userService.getUserByUsername(username).getId();
        List<Role> roles = new ArrayList<>();
        List<UserRole> urs = userRoleService.listAllByUid(uid);
        urs.forEach(ur -> roles.add(roleDao.findById(ur.getRid()).get()));
        return roles;
    }
//    @Override
//    public List<Menu> getMenuList(Long adminId) {
//        return roleDao.getMenuList(adminId);
//    }
//
//    @Override
//    public List<Menu> listMenu(Long roleId) {
//        return roleDao.getMenuListByRoleId(roleId);
//    }
//
//    @Override
//    public List<Resource> listResource(Long roleId) {
//        return roleDao.getResourceListByRoleId(roleId);
//    }
//
//    @Override
//    public int allocMenu(Long roleId, List<Long> menuIds) {
//        //先删除原有关系
//        RoleMenuRelationExample example=new RoleMenuRelationExample();
//        example.createCriteria().andRoleIdEqualTo(roleId);
//        roleMenuRelationMapper.deleteByExample(example);
//        //批量插入新关系
//        for (Long menuId : menuIds) {
//            RoleMenuRelation relation = new RoleMenuRelation();
//            relation.setRoleId(roleId);
//            relation.setMenuId(menuId);
//            roleMenuRelationMapper.insert(relation);
//        }
//        return menuIds.size();
 //   }

//    @Override
//    public int allocResource(Long roleId, List<Long> resourceIds) {
//        //先删除原有关系
//        RoleResourceRelationExample example=new RoleResourceRelationExample();
//        example.createCriteria().andRoleIdEqualTo(roleId);
//        roleResourceRelationMapper.deleteByExample(example);
//        //批量插入新关系
//        for (Long resourceId : resourceIds) {
//            RoleResourceRelation relation = new RoleResourceRelation();
//            relation.setRoleId(roleId);
//            relation.setResourceId(resourceId);
//            roleResourceRelationMapper.insert(relation);
//        }
//        resourceService.initResourceRolesMap();
//        return resourceIds.size();
//    }
}
