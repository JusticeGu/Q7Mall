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
    private String path;
    /**
     * Menu name.
     */
    private String name;
    /**
     * Menu name in Chinese.
     */
    private String nameZh;

    /**
     * Menu icon class(use element-ui icons).
     */
    private String iconCls;

    /**
     * Front-end component name corresponding to menu.
     */
    private String component;
    /**
     * Parent menu.
     */
    private Long parentId;
    /**
     * Transient property for storing children menus.
     */
    @Transient
    private List<Menu> children;
}


