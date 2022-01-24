package com.q7w.Service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.q7w.Service.ResourceService;
import com.q7w.Service.RoleResourceService;
import com.q7w.Service.RoleService;
import com.q7w.common.constant.AuthConstant;

import com.q7w.common.constant.GlobalConstants;
import com.q7w.common.service.RedisService;
import com.q7w.DAO.ResourceDao;
import com.q7w.DAO.RoleDao;
import com.q7w.DAO.RoleResourceDao;
import com.q7w.Entity.Resource;
import com.q7w.Entity.Role;
import com.q7w.Entity.RoleResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author xiaogu
 * @date 2021/4/6 22:04
 **/
@Service
public class ResourceServiceimpl implements ResourceService {
    @Autowired
    private ResourceDao resourceDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private RoleResourceDao roleResourceDao;
    @Autowired
    RoleResourceService roleResourceService;
    @Autowired
    RoleService roleService;
    @Autowired
    private RedisService redisService;
    @Value("${spring.application.name}")
    private String applicationName;
    @Override
    public int create(Resource resource) {
        resource.setCreateTime(new Date().getTime());
        resourceDao.save(resource);
        initResourceRolesMap();
        return 1;
    }

    @Override
    public boolean needFilter(String requestAPI) {
        List<Resource> resources = resourceDao.findAll();
        for (Resource r: resources) {
            // 这里我们进行前缀匹配，拥有父权限就拥有所有子权限
            if (requestAPI.startsWith(r.getUrl())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int update(Long id, Resource umsResource) {
        umsResource.setId(id);
        resourceDao.save(umsResource);
        initResourceRolesMap();
        return 1;
    }

    @Override
    public List<Resource> getItem(Long id) {
        return resourceDao.findAll();
    }

    @Override
    public int delete(Long id) {
        resourceDao.deleteById(id);
        initResourceRolesMap();
        return 1;
    }

//    @Override
//    public List<Resource> list(Long categoryId, String nameKeyword, String urlKeyword, Integer pageSize, Integer pageNum) {
//        PageHelper.startPage(pageNum,pageSize);
//        ResourceExample example = new UmsResourceExample();
//        ResourceExample.Criteria criteria = example.createCriteria();
//        if(categoryId!=null){
//            criteria.andCategoryIdEqualTo(categoryId);
//        }
//        if(StrUtil.isNotEmpty(nameKeyword)){
//            criteria.andNameLike('%'+nameKeyword+'%');
//        }
//        if(StrUtil.isNotEmpty(urlKeyword)){
//            criteria.andUrlLike('%'+urlKeyword+'%');
//        }
//        return resourceMapper.selectByExample(example);
//    }

    @Override
    public List<Resource> listAll() {
        return resourceDao.findAll();
    }
    @Override
    public List<Resource> listbytype(Integer type) {
        return resourceDao.findAllByType(type);
    }
    @Override
    public Map<String,List<String>> initResourceRolesMap() {
        Map<String,List<String>> resourceRoleMap = new TreeMap<>();
        List<Resource> resourceList = resourceDao.findAll();//所有路由
        List<Role> roleList = roleDao.findAll();
        List<RoleResource> relationList = roleResourceDao.findAll();
        for (Resource resource : resourceList) {
            Set<Long> roleIds = relationList.stream().filter(item -> item.getPid().equals(resource.getId())).map(RoleResource::getRid).collect(Collectors.toSet());
            List<String> roleNames = roleList.stream().filter(item -> roleIds.contains(item.getId())).map(item -> item.getId() + "_" + item.getName()).collect(Collectors.toList());
            resourceRoleMap.put(resource.getMethod() +"_"+ resource.getUrl(),roleNames);}

        redisService.del(AuthConstant.RESOURCE_ROLES_MAP_KEY);
        redisService.hSetAll(AuthConstant.RESOURCE_ROLES_MAP_KEY, resourceRoleMap);
        return resourceRoleMap;
    }
    public boolean refreshPermRolesRules() {
//        redisTemplate.delete(Arrays.asList(GlobalConstants.URL_PERM_ROLES_KEY, GlobalConstants.BTN_PERM_ROLES_KEY));
//        List<Resource> permissions = this.listAll();
//        if (CollectionUtil.isNotEmpty(permissions)) {
//            // 初始化URL【权限->角色(集合)】规则
//            List<Resource> urlPermList = permissions.stream()
//                    .filter(item -> StrUtil.isNotBlank(item.getUrl()))
//                    .collect(Collectors.toList());
//            if (CollectionUtil.isNotEmpty(urlPermList)) {
//                Map<String, List<String>> urlPermRoles = new HashMap<>();
//                urlPermList.stream().forEach(item -> {
//                    String perm = item.getUrl();
//                    List<String> roles = item.getId();
//                    urlPermRoles.put(perm, roles);
//                });
//                redisService.hSetAll(GlobalConstants.URL_PERM_ROLES_KEY,urlPermRoles);
//                //redisTemplate.opsForHash().putAll(, urlPermRoles);
//              //  redisTemplate.convertAndSend("cleanRoleLocalCache", "true");
//            }
//            // 初始化URL【按钮->角色(集合)】规则
//            List<Resource> btnPermList = permissions.stream()
//                    .filter(item -> StrUtil.isNotBlank(item.getBtmperms()))
//                    .collect(Collectors.toList());
//            if (CollectionUtil.isNotEmpty(btnPermList)) {
//                Map<String, List<String>> btnPermRoles = CollectionUtil.newHashMap();
//                btnPermList.stream().forEach(item -> {
//                    String perm = item.getBtmperms();
//                    List<String> roles = item.;
//                    btnPermRoles.put(perm, roles);
//                });
//                redisTemplate.opsForHash().putAll(GlobalConstants.BTN_PERM_ROLES_KEY, btnPermRoles);
//            }
//        }
        return true;
    }
    @Override
    public List<Resource> listPermsByRoleId(Long rid,Integer type) {
        List<Resource> res = (List<Resource>)redisService.get("res:"+rid+":"+type);
        if(res!=null){return res;}else{
        List<RoleResource> rps = roleResourceService.findAllByRid(rid);
        List<Resource> resources = new ArrayList<>();
        rps.forEach((rp) -> {
            Resource ress = resourceDao.findById(rp.getPid()).get();
            if (ress.getType()==type){
            resources.add(ress);
            }});
        redisService.set("res:"+rid+":"+type,resources,60);
        return resources;}
    }

    @Override
    public List<Resource> listPermsByRoleId(Long rid) {
        List<RoleResource> rps = roleResourceService.findAllByRid(rid);
        List<Resource> resources = new ArrayList<>();
        rps.forEach(rp -> resources.add(resourceDao.findById(rp.getPid()).get()));
        return resources;
    }

    @Override
    public Set<String> listPermissionURLsByUser(Long uid) {
        List<Role> roles = roleService.listRolesByUser(uid);
        Set<String> URLs = new HashSet<>();
        roles.forEach(r -> {
            List<RoleResource> rps = roleResourceService.findAllByRid(r.getId());
            rps.forEach((rp) -> {
                Resource res = resourceDao.findById(rp.getPid()).get();
//                if (res.getType()==2){
//                    URLs.add(res.getUrl());
//                }
                URLs.add(res.getUrl());
                });
        });

        return URLs;
    }

    @Override
    public Object getrolrresmap() {
        initResourceRolesMap();
        Map<Object, Object> resourceRolesMap = redisService.hGetAll(AuthConstant.RESOURCE_ROLES_MAP_KEY);
        return resourceRolesMap;
    }
}
