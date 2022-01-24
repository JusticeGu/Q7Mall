package com.q7w.common.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 登录用户信息
 * Created by macro on 2020/6/19.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private Integer status;
    private String clientId;
    private List<String> roles;
    private boolean accountNonExpired;//是否过期
    private boolean accountNonLocked;//是否锁定
    private boolean credentialsNonExpired;//认证信息是否不存在
    private boolean enabled;//是否可用

}
