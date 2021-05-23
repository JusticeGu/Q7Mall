package com.q7w.authorization;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;

import com.q7w.common.constant.AuthConstant;
import com.q7w.common.domain.Payload;
import com.q7w.common.domain.UserDto;
import com.q7w.common.service.RedisService;
import com.q7w.component.RestAuthenticationEntryPoint;
import com.q7w.config.IgnoreUrlsConfig;
import com.nimbusds.jose.JWSObject;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 鉴权管理器，用于判断是否有资源的访问权限
 * Created by macro on 2020/6/19.
 */
@Component
@ConfigurationProperties("secure.ignore")
@Data
@Slf4j
public class AuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {
    private List<String> adminurls;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private IgnoreUrlsConfig ignoreUrlsConfig;
    @Autowired
    RedisService redisService;

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {
        ServerHttpRequest request = authorizationContext.getExchange().getRequest();
        URI uri = request.getURI();
        String restPath = request.getMethodValue() + "_" + request.getURI().getPath();
        PathMatcher pathMatcher = new AntPathMatcher();
        //白名单路径直接放行
        List<String> ignoreUrls = ignoreUrlsConfig.getUrls();
        for (String ignoreUrl : ignoreUrls) {
            if (pathMatcher.match(ignoreUrl, uri.getPath())) {
                return Mono.just(new AuthorizationDecision(true));
            }
        }
        //对应跨域的预检请求直接放行
        if(request.getMethod()==HttpMethod.OPTIONS){
            return Mono.just(new AuthorizationDecision(true));
        }
        //token请求头检查
        String token = request.getHeaders().getFirst(AuthConstant.JWT_TOKEN_HEADER);
        if(!StrUtil.contains(token,AuthConstant.JWT_TOKEN_PREFIX)){
            return Mono.just(new AuthorizationDecision(false));
        }
//

        List<String> manageurls = adminurls;
        // 非管理端路径直接放行
//        if(null != manageurls&& !Arrays.asList(manageurls).contains(uri.getPath())){
//            return Mono.just(new AuthorizationDecision(true));
//        }
        if(!pathMatcher.match(adminurls.get(0), uri.getPath())){
            return Mono.just(new AuthorizationDecision(true));
        }
//            for (String adminurl : manageurls) {
//                if (pathMatcher.match(adminurl, uri.getPath()) && !AuthConstant.ADMIN_CLIENT_ID.equals(userDto.getClient_id())) {
//                    return Mono.just(new AuthorizationDecision(false));
//                }
//            }
        //管理端路径需校验权限
     //   Map<Object, Object> resourceRolesMap = redisTemplate.opsForHash().entries(AuthConstant.RESOURCE_ROLES_MAP_KEY);
        Map<Object, Object> resourceRolesMap =redisService.hGetAll(AuthConstant.RESOURCE_ROLES_MAP_KEY);
        Iterator<Object> iterator = resourceRolesMap.keySet().iterator();
        List<String> authorities = new ArrayList<>();
        //Set<String> authorities = new HashSet<>();
        while (iterator.hasNext()) {
            String pattern = (String) iterator.next();
            if (pathMatcher.match(pattern, restPath)) {
                authorities.addAll(Convert.toList(String.class, resourceRolesMap.get(pattern)));
            }
        }
        authorities = authorities.stream().map(i -> i = AuthConstant.AUTHORITY_PREFIX + i).collect(Collectors.toList());
        //认证通过且角色匹配的用户可访问当前路径
        log.info("path：{},user_role：{}", restPath,authorities);
        return mono
                .filter(Authentication::isAuthenticated)
                .flatMapIterable(Authentication::getAuthorities)
                .map(GrantedAuthority::getAuthority)
                .any(authorities::contains)
                .map(AuthorizationDecision::new)
                .defaultIfEmpty(new AuthorizationDecision(false));
    }

}
