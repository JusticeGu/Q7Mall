package com.q7w.auth.Entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author xiaogu
 * @date 2021/3/30 19:49
 **/

@Data
@Entity
@Table(name = "auth_user_profile")
public class Userpro extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long uid;
    private String realname;
    private String phone;
    private String email;
    private String address;
    private String photo;
    private boolean is_varify;//实名认证
    private boolean is_company;//是否机构账号
    private boolean is_comuser;//是否机构员工账号
    private String comnumber;//工号
    private boolean is_superuser;//是否官方账号-进后台权限

}
