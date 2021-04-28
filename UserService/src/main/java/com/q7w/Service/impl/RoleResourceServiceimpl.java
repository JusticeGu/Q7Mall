package com.q7w.Service.impl;

import com.q7w.DAO.RoleResourceDao;
import com.q7w.Entity.Resource;
import com.q7w.Entity.Role;
import com.q7w.Entity.RoleResource;
import com.q7w.Service.ResourceService;
import com.q7w.Service.RoleResourceService;
import com.q7w.Service.RoleService;
import com.q7w.common.constant.AuthConstant;
import com.q7w.common.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * @author xiaogu
 * @date 2021/4/24 16:09
 **/
@Service
public class RoleResourceServiceimpl implements RoleResourceService {
    @Autowired
    RoleResourceDao roleResourceDao;
    @Autowired
    RedisService redisService;
    @Autowired
    RoleService roleService;
    @Autowired
    ResourceService resourceService;
    @Value("${spring.application.name}")
    private String applicationName;
    @Override
    public List<RoleResource> findAllByRid(Long rid) {
        return roleResourceDao.findAllByRid(rid);
    }

    @Override
    public void savePermChanges(Long rid, List<Long> resourceIds) {
        if (roleResourceDao.findAllByRid(rid).size()!=0){
            roleResourceDao.deleteAllByRid(rid);
        }
        resourceIds.forEach(p -> {
            RoleResource rr = new RoleResource();
            rr.setRid(rid);
            rr.setPid(p);
            roleResourceDao.save(rr);
        });
        initResourceRolesMap();
    }

    public Map<String,List<String>> initResourceRolesMap() {
        Map<String,List<String>> resourceRoleMap = new TreeMap<>();
        List<Resource> resourceList = resourceService.listAll();
        List<Role> roleList = roleService.list();
        List<RoleResource> relationList = roleResourceDao.findAll();
        for (Resource resource : resourceList) {
            Set<Long> roleIds = relationList.stream().filter(item -> item.getPid().equals(resource.getId())).map(RoleResource::getRid).collect(Collectors.toSet());
            List<String> roleNames = roleList.stream().filter(item -> roleIds.contains(item.getId())).map(item -> item.getId() + "_" + item.getName()).collect(Collectors.toList());
            resourceRoleMap.put("/"+applicationName+resource.getUrl(),roleNames);
        }
        redisService.del(AuthConstant.RESOURCE_ROLES_MAP_KEY);
        redisService.hSetAll(AuthConstant.RESOURCE_ROLES_MAP_KEY, resourceRoleMap);
        return resourceRoleMap;
    }

    @Override
    public int delresource(Long resid) {
        roleResourceDao.deleteAllByPid(resid);
        initResourceRolesMap();
        return 1;
    }

    @Override
    public int delrole(Long rid) {
        roleResourceDao.deleteAllByRid(rid);
        initResourceRolesMap();
        return 1;
    }
}
