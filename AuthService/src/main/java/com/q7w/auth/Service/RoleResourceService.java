package com.q7w.auth.Service;

import com.q7w.auth.Entity.Resource;
import com.q7w.auth.Entity.RoleResource;

import java.util.List;

/**
 * @author xiaogu
 * @date 2021/4/24 16:09
 **/
public interface RoleResourceService {
    List<RoleResource> findAllByRid(Long rid);
    public void savePermChanges(Long rid, List<Resource> resources);
}
