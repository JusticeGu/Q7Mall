package com.q7w.Service.impl;

import com.q7w.Service.UserFeign;
import org.springframework.stereotype.Service;

@Service
public class UserFeignimpl implements UserFeign {
    @Override
    public String getusername() {
        return "sys";
    }
}
