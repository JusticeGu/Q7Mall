package com.q7w.Entity;



import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author xiaogu
 * @date 2020/11/27 14:30
 **/
@Data
@Entity
@Table(name = "auth_user")
public class User extends BaseEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    @JsonIgnore
    private String password;
    private String email;
    private boolean accountNonExpired;//是否过期
    private boolean accountNonLocked;//是否锁定
    private boolean credentialsNonExpired;//认证信息是否不存在
    private boolean enabled;//是否可用
    @Transient
    private List<Userpro> userpros;


}

