package com.q7w.Service;

import com.q7w.Service.impl.UserFeignimpl;
import com.q7w.common.result.CommonResult;
import com.q7w.config.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author xiaogu
 * @date 2021/8/9 15:43
 **/
@FeignClient(value = "UserCoreService",configuration = FeignConfiguration.class,fallback = UserFeignimpl.class)
public interface UserFeign {
    @GetMapping(value = "/v1/user/currentuserid")
    Long getcurrentuid();
}
