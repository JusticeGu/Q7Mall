package com.q7w.Service.impl;

import com.q7w.Service.ResourceService;
import com.q7w.common.constant.AuthConstant;
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
    public Map<String,List<String>> initResourceRolesMap() {
        Map<String,List<String>> resourceRoleMap = new TreeMap<>();
        List<Resource> resourceList = resourceDao.findAll();
        List<Role> roleList = roleDao.findAll();
        List<RoleResource> relationList = roleResourceDao.findAll();
        for (Resource resource : resourceList) {
            Set<Integer> roleIds = relationList.stream().filter(item -> item.getPid().equals(resource.getId())).map(RoleResource::getRid).collect(Collectors.toSet());
            List<String> roleNames = roleList.stream().filter(item -> roleIds.contains(item.getId())).map(item -> item.getId() + "_" + item.getName()).collect(Collectors.toList());
            resourceRoleMap.put("/"+applicationName+resource.getUrl(),roleNames);
        }
        redisService.del(AuthConstant.RESOURCE_ROLES_MAP_KEY);
        redisService.hSetAll(AuthConstant.RESOURCE_ROLES_MAP_KEY, resourceRoleMap);
        return resourceRoleMap;

    }
}
