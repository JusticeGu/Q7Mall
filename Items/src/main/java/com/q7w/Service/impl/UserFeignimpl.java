package com.q7w.Service.impl;

import com.q7w.Service.UserFeign;
import org.springframework.stereotype.Service;

/**
 * @author xiaogu
 * @date 2021/4/10 12:16
 **/
@Service
public class UserFeignimpl implements UserFeign {
    @Override
    public String getusername() {
        return "sys";
    }
}
