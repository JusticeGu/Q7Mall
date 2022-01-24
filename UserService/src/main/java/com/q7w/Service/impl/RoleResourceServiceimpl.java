package com.q7w.Service.impl;

import com.q7w.DAO.RoleResourceDao;
import com.q7w.Entity.Resource;
import com.q7w.Entity.Role;
import com.q7w.Entity.RoleResource;
import com.q7w.Mapper.RoleResourceMapper;
import com.q7w.Service.ResourceService;
import com.q7w.Service.RoleResourceService;
import com.q7w.Service.RoleService;
import com.q7w.common.constant.AuthConstant;
import com.q7w.common.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    RoleResourceMapper roleResourceMapper;
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
    @Modifying
    @Transactional
    public void savePermChanges(Long rid, List<Long> resourceIds,int type,Long modelid) {
        if (roleResourceDao.findAllByRid(rid).size()!=0){
            //roleResourceDao.deleteAllByRid(rid);
            roleResourceDao.delAllByRidTypeModel(rid,type,modelid);
        }
        resourceIds.forEach(p -> {
            RoleResource rr = new RoleResource();
            rr.setRid(rid);
            rr.setPid(p);
            roleResourceDao.save(rr);
        });
        resourceService.initResourceRolesMap();
    }



    @Override
    public int delresource(Long resid) {
        roleResourceDao.deleteAllByPid(resid);
        resourceService.initResourceRolesMap();
        return 1;
    }

    @Override
    public int delrole(Long rid) {
        roleResourceDao.deleteAllByRid(rid);
        resourceService.initResourceRolesMap();
        return 1;
    }
}
