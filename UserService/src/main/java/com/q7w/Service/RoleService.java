package com.q7w.Service;

import com.q7w.Entity.Menu;
import com.q7w.Entity.Resource;
import com.q7w.Entity.Role;

import java.util.List;

/**
 * @author xiaogu
 * @date 2021/4/6 19:49
 **/
public interface RoleService {
    /**
     * 添加角色
     */
    int create(Role role);

    /**
     * 修改角色信息
     */
    int update(Long id, Role role);

    /**
     * 批量删除角色
     */
    int delete(List<Long> ids);

    /**
     * 获取所有角色列表
     */
    List<Role> list();
    Role findById(Long id);
    List<Role> listRolesByUser(Long uid);
    /**
     * 分页获取角色列表
     */
    List<Role> list(String keyword, Integer pageSize, Integer pageNum);

    /**
     * 根据管理员ID获取对应菜单
     */
  //  List<Menu> getMenuList(Long adminId);

    /**
     * 获取角色相关菜单
     */
    List<Menu> listroleMenu(Long roleId);

    /**
     * 获取角色相关资源
     */
    List<Resource> listroleResource(Long roleId);

    /**
     * 给角色分配菜单
     */
//    @Transactional
    int allocMenu(Long roleId, List<Long> menuIds);

    /**
     * 给角色分配资源
     */
//    @Transactional
    int allocResource(Long roleId, List<Long> resourceIds);
    int allocuserrole(Long uid,List<Long> rids);
    List<Resource> listroletypeRes(Long roleId,int type);
}
