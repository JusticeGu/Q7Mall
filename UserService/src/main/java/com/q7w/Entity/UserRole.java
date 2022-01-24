package com.q7w.Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author xiaogu
 * @date 2021/4/5 19:17
 **/
@Data
@Entity
@Table(name = "auth_user_role")
@ToString
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class UserRole implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * User id.
     */
    private Long uid;

    /**
     * Role id.
     */
    private Long rid;
    @Transient
    private List<Long> rids;
}

