package com.q7w.Entity;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

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

}