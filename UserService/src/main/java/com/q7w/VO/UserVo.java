package com.q7w.VO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.q7w.Entity.Userpro;
import lombok.Data;

import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;

/**
 * @author xiaogu
 * @date 2021/5/23 19:13
 **/
@Data
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class UserVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String username;
    private String password;
    private String email;
    private List roleids;
    private boolean enabled;//是否可用
    @Transient
    private Userpro userpros;
}
