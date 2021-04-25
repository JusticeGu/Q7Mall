package com.q7w.auth.Service.impl;

import com.q7w.auth.DAO.MenuDao;
import com.q7w.auth.Entity.Menu;
import com.q7w.auth.Entity.RoleMenu;
import com.q7w.auth.Entity.User;
import com.q7w.auth.Entity.UserRole;
import com.q7w.auth.Service.MenuService;
import com.q7w.auth.Service.RoleMenuService;
import com.q7w.auth.Service.UserRoleService;
import com.q7w.auth.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaogu
 * @date 2021/4/23 23:37
 **/
@Service
public class MenuServiceimpl implements MenuService {
    @Autowired
    MenuDao menuDao;
    @Autowired
    UserService userService;
    @Autowired
    UserRoleService userRoleService;
    @Autowired
    RoleMenuService roleMenuService;
    @Override
    public int create(Menu menu) {
        return 0;
    }

    @Override
    public int update(Long id, Menu menu) {
        return 0;
    }

    @Override
    public int delete(List<Long> ids) {
        return 0;
    }

    @Override
    public List<Menu> list() {
        return null;
    }

    @Override
    public List<Menu> getAllByParentId(Long parentId) {
        return menuDao.findAllByParentId(parentId);
    }

    @Override
    public List<Menu> getMenusByCurrentUser() {
        String username = userService.getcurrertusername();
        User user = userService.getUserByUsername(username);
        List<UserRole> userRoleList =userRoleService.listAllByUid(user.getId());
        List<Menu> menus = new ArrayList<>();

        userRoleList.forEach(ur -> {
            List<RoleMenu> rms = roleMenuService.findAllByRid(ur.getRid());
            rms.forEach(rm -> {
                Menu menu = menuDao.findById(rm.getMid()).get();
                // 防止多角色状态下菜单重复
                if(!menus.contains(menu)) {
                    menus.add(menu);
                }
            });
        });
        handleMenus(menus);
        return menus;
    }

    @Override
    public List<Menu> getMenusByRoleId(Long rid) {
        List<Menu> menus = new ArrayList<>();
        List<RoleMenu> rms = roleMenuService.findAllByRid(rid);

        rms.forEach(rm -> menus.add(menuDao.findById(rm.getMid()).get()));

        handleMenus(menus);
        return menus;
    }

    @Override
    public void handleMenus(List<Menu> menus) {
        menus.forEach(m -> {
            List<Menu> children = getAllByParentId(m.getId());
            m.setChildren(children);
        });
        menus.removeIf(m -> m.getParentId() != 0);
    }
}
