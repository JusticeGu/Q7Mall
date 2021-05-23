package com.q7w.Entity;

import lombok.Data;
import lombok.ToString;


import javax.persistence.*;
import java.io.Serializable;

/**
 * @author xiaogu
 * @date 2021/4/5 19:00
 **/
@Data
@Entity
@Table(name = "auth_Resource")
@ToString

public class Resource extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;//权限名称

    private String dscp;//权限简介

    private String url;//授权url

    private String method;//请求方法

    private int type;//权限类型 1-接口权限 2-按钮权限
}
