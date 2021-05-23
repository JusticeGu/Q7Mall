package com.q7w.Service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.q7w.DAO.MenuDao;
import com.q7w.Entity.Menu;
import com.q7w.Entity.RoleMenu;
import com.q7w.Entity.UserRole;
import com.q7w.Service.MenuService;
import com.q7w.Service.RoleMenuService;
import com.q7w.Service.UserRoleService;
import com.q7w.Service.UserService;
import com.q7w.VO.TreeVO;
import com.q7w.common.exception.GlobalException;
import com.q7w.common.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    @Autowired
    private RedisService redisService;
    private Menu findmenubyname(String name){
        return menuDao.findByName(name);
    }
    private Menu findmenubyid(Long id){
        Menu menu = menuDao.findById(id).get();
        if (menu==null){
            throw new GlobalException("808X01","菜单不存在");}
        return menu;
    }
    @Override
    public int create(Menu menu) {
        if (findmenubyname(menu.getName())!=null){return 2;}
        menuDao.save(menu);
        return 1;
    }

    @Override
    public int update(Long id, Menu menu) {
        Menu menudb = findmenubyid(id);
        menudb.setName(menu.getName());
        menudb.setLabel(menu.getLabel());
        menudb.setComponent(menu.getComponent());
        menudb.setIconCls(menu.getIconCls());
        menudb.setLabel(menu.getLabel());
        menuDao.save(menudb);
        return 1;
    }

    @Override
    public int delete(List<Long> ids) {
        try{
            menuDao.deleteById(ids.get(1));
            return 1;
        }catch (EmptyResultDataAccessException e)
        {
            return 2;
        }

    }

    @Override
    public List list() {
        List<Menu> menus = menuDao.findAll();
        handleMenus(menus);
        return menus;
    }

    public static List<TreeVO> recursionForTreeSelect(Long parentId, List<Menu> menuList) {
        List<TreeVO> list = new ArrayList<>();
        Optional.ofNullable(menuList).orElse(new ArrayList<>())
                .stream()
                .filter(menu -> menu.getParentId().equals(parentId))
                .forEach(menu -> {
                    TreeVO treeVO = new TreeVO();
                    treeVO.setId(menu.getId());
                    treeVO.setLabel(menu.getName());
                    List<TreeVO> children = recursionForTreeSelect(menu.getId(), menuList);
                    if (CollectionUtil.isNotEmpty(children)) {
                        treeVO.setChildren(children);
                    }
                    list.add(treeVO);
                });
        return list;
    }
    @Override
    public List<Menu> getAllByParentId(Long parentId) {
        return menuDao.findAllByParentId(parentId);
    }

    @Override
    public List<Menu> getMenusByCurrentUser() {
        Long uid = userService.getcurrertuserid();
        Object usermenu = redisService.get("menus:uid:"+uid);
        if(usermenu == null){
       // User user = userService.getUserByUsername(username);
        List<UserRole> userRoleList =userRoleService.listAllByUid(uid);
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
        redisService.set("menus:uid:"+uid,menus,3600);
        return menus;}
        else {
            return (List<Menu>)usermenu;
        }
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
