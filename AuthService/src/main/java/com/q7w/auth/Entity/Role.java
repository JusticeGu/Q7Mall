package com.q7w.auth.Entity;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author xiaogu
 * @date 2020/11/27 14:32
 **/
@Entity(name = "t_role")
@Data
public class Role extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//角色id
    private String name;
    private String description;
    private Integer adminCount;
    private Integer status;
    @Transient
    private List<Resource> resources;
    /**
     * Transient property for storing menus owned by current role.
     */
    @Transient
    private List<Menu> menus;
}