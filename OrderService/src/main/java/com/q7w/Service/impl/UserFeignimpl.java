package com.q7w.Service.impl;

import com.q7w.Service.UserFeign;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class UserFeignimpl implements UserFeign {
    @Override
    public String getusername() {
        return null;
    }

    @Override
    public Long getuid() {
        return null;
    }
}
