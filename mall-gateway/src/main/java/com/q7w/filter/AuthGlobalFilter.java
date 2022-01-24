package com.q7w.filter;

import cn.hutool.core.util.StrUtil;

import cn.hutool.json.JSONUtil;
import com.nimbusds.jose.JWSObject;
import com.q7w.common.constant.AuthConstant;
import com.q7w.common.domain.Payload;
import com.q7w.common.result.ExceptionMsg;
import com.q7w.common.service.RedisService;
import com.q7w.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import org.springframework.http.server.reactive.ServerHttpResponse;
import java.text.ParseException;

/**
 * 将登录用户的JWT转化成用户信息的全局过滤器
 * Created by macro on 2020/6/17.
 */
@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {
    @Autowired
    RedisService redisService;
    private static Logger LOGGER = LoggerFactory.getLogger(AuthGlobalFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpResponse response = exchange.getResponse();
        String token = exchange.getRequest().getHeaders().getFirst(AuthConstant.JWT_TOKEN_HEADER);
        if (StrUtil.isEmpty(token)) {
            return chain.filter(exchange);
        }
        try {
            //从token中解析用户信息并设置到Header中去
            String realToken = token.replace(AuthConstant.JWT_TOKEN_PREFIX, "");
            JWSObject jwsObject = JWSObject.parse(realToken);
            String userStr = jwsObject.getPayload().toString();
            Payload userDto = JSONUtil.toBean(userStr, Payload.class);
            //黑名单用户禁止访问//jti过期禁止访问
            if (redisService.sIsMember("auth:Blacktable",userDto.getJti())){
                return WebUtils.writeFailedToResponse(response,ExceptionMsg.Unauthorized,
                        "登录已过期或您已被加入黑名单请重新登录");
            }
            //禁止多端登录
//            String jti = (String)redisService.get("auth:User:"+userDto.getId());
//            if (jti==null||!jti.equals(userDto.getJti())){
//                return WebUtils.writeFailedToResponse(response,ExceptionMsg.BELOGOUT,
//                        "您的账号已在其他设备登录，请重新登录");
//
//            }
            LOGGER.info("AuthGlobalFilter.filter() user:{}",userStr);
            ServerHttpRequest request = exchange.getRequest().mutate().header(AuthConstant.USER_TOKEN_HEADER, userStr).build();
            exchange = exchange.mutate().request(request).build();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
