package com.q7w.Entity;
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
@Table(name = "auth_role_Resource")
@ToString
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class RoleResource extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private Long rid;

    private Long pid;
    @Transient
    private List<Long> pids;
}

