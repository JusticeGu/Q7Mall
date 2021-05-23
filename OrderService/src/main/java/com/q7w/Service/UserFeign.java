package com.q7w.Service;

import com.q7w.Service.impl.ItemsFeignFallback;
import com.q7w.Service.impl.UserFeignimpl;
import com.q7w.config.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author xiaogu
 * @date 2021/4/23 20:31
 **/
@FeignClient(value = "ItemsCoreService",configuration = FeignConfiguration.class,fallback = UserFeignimpl.class)
public interface UserFeign {
    @GetMapping("/api/user/getusername")
    public String getusername();
    @GetMapping("/api/user/getuserid")
    public Long getuid();
}