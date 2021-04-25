package com.q7w.Service.impl;

import com.q7w.DAO.RoleResourceDao;
import com.q7w.Entity.Resource;
import com.q7w.Entity.RoleResource;
import com.q7w.Service.RoleResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xiaogu
 * @date 2021/4/24 16:09
 **/
@Service
public class RoleResourceServiceimpl implements RoleResourceService {
    @Autowired
    RoleResourceDao roleResourceDao;
    @Override
    public List<RoleResource> findAllByRid(Long rid) {
        return roleResourceDao.findAllByRid(rid);
    }

    @Override
    public void savePermChanges(Long rid, List<Resource> resources) {
        roleResourceDao.deleteAllByRid(rid);
        resources.forEach(p -> {
            RoleResource rr = new RoleResource();
            rr.setRid(rid);
            rr.setPid(p.getId());
            roleResourceDao.save(rr);
        });
    }
}
