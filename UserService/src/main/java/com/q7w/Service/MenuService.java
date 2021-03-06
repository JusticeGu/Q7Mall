package com.q7w.Service;

import com.q7w.Entity.Menu;
import java.util.List;

/**
 * @author xiaogu
 * @date 2021/4/23 23:37
 **/
public interface MenuService {
    /**
     * 添加角色
     */
    int create(Menu menu);

    /**
     * 修改角色信息
     */
    int update(Long id, Menu menu);

    /**
     * 批量删除角色
     */
    int delete(List<Long> ids);

    /**
     * 获取所有角色列表
     */
    List list();
    List<Menu> getAllByParentId(Long parentId);
    public List<Menu> getMenusByCurrentUser();
    public List<Menu> getMenusByRoleId(Long rid);
    public void handleMenus(List<Menu> menus);
}
