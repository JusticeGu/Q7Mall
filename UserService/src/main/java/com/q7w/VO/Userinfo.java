package com.q7w.VO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.q7w.Entity.Resource;
import com.q7w.Entity.Role;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * @author xiaogu
 * @date 2021/5/20 18:16
 **/
@Data
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Userinfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String nickname;
    private String avatar;
    private List roles;
    private Set perms;

}
