package com.q7w.Service.impl;

import com.q7w.Service.UserFeign;
import com.q7w.common.exception.GlobalException;
import com.q7w.common.result.CommonResult;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class UserFeignimpl implements UserFeign {
    @Override
    public Long getcurrentuid(){

        throw new GlobalException("501001","网关获取用户信息异常");
    }
}
