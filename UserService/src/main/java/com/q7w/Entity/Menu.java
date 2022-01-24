package com.q7w.Entity;

/**
 * @author xiaogu
 * @date 2021/4/5 19:15
 **/
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "auth_menu")
@ToString
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Menu extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    /**
     * Menu access path.
     */
    private String path;//前端路径
    /**
     * Menu name.
     */
    private String name;//菜单名称

    /**
     * Menu icon class(use element-ui icons).
     */
    private String iconCls;//图标
    /**
     * Front-end component name corresponding to menu.
     */
    private String component;//前端组件名称
    /**
     * Parent menu.
     */
    private Long parentId;
    /**
     * 重定向页面
     */
    private String redirect;
    private boolean status;
    /**
     * Transient property for storing children menus.
     */
    @Transient
    private List<Menu> children;
    @Transient
    private List roles;
}


