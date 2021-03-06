package com.q7w.Service;

import com.q7w.Entity.Resource;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author xiaogu
 * @date 2021/4/6 21:51
 **/
public interface ResourceService {
    /**
     * 添加资源
     */
    int create(Resource resource);
    /**
     * 比较权限
     */
    boolean needFilter(String requestAPI);
    List<Resource> listbytype(Integer type);
    List<Resource> listPermsByRoleId(Long rid);
    List<Resource> listPermsByRoleId(Long rid,Integer type);
    Set<String> listPermissionURLsByUser(Long uid);
    /**
     * 修改资源
     */
    int update(Long id, Resource resource);

    /**
     * 获取资源详情
     */
    List<Resource> getItem(Long id);

    /**
     * 删除资源
     */
    int delete(Long id);

    /**
     * 分页查询资源
     */
   // List<Resource> list(Long categoryId, String nameKeyword, String urlKeyword, Integer pageSize, Integer pageNum);

    /**
     * 查询全部资源
     */
    List<Resource> listAll();

    /**
     * 初始化资源角色规则
     */
    Map<String,List<String>> initResourceRolesMap();
    Object getrolrresmap();
}
