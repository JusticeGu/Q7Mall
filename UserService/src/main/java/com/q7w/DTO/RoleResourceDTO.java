package com.q7w.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * @author xiaogu
 * @date 2021/5/24 15:52
 **/
@Data
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class RoleResourceDTO {
    private static final long serialVersionUID = 1L;
    private List<Long> rids;
    private int type;
    private Long modelid;
}
