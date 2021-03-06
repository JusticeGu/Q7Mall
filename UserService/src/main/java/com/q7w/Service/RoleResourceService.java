package com.q7w.Service;

import com.q7w.Entity.Resource;
import com.q7w.Entity.RoleResource;

import java.util.List;

/**
 * @author xiaogu
 * @date 2021/4/24 16:09
 **/
public interface RoleResourceService {
    List<RoleResource> findAllByRid(Long rid);
    public void savePermChanges(Long rid, List<Long> resourceIds);
    int delresource(Long resid);
    int delrole(Long rid);
}
