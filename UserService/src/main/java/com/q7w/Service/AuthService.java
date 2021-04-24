package com.q7w.Service;


import com.q7w.common.result.ResponseData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * 认证服务远程调用
 * Created by macro on 2020/7/19.
 */
@FeignClient("AuthService")
public interface AuthService {

    @PostMapping(value = "/oauth/token")
    ResponseData getAccessToken(@RequestParam Map<String, String> parameters);

}
