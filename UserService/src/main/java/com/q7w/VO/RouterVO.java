package com.q7w.VO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
/**
 * @author xiaogu
 * @date 2021/5/24 13:40
 **/



/**
 * @author haoxr
 * @date 2020-11-06
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RouterVO {
    private static final long serialVersionUID = 1L;
    private String path;

    private String component;

    private String redirect;

    private boolean alwaysShow;

    private String name;

    private Boolean hidden;

    private Meta meta;

    @Data
    @AllArgsConstructor
    public class Meta {
        private String title;
        private String icon;
        private List<Integer> roles;
    }
    private List<RouterVO> children;

}

