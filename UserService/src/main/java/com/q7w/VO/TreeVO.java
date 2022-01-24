package com.q7w.VO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import java.util.List;
/**
 * @author xiaogu
 * @date 2021/5/22 20:55
 **/


@Data
public class TreeVO {

    private Long id;

    private String label;
    private boolean status;
    private String name;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private List<TreeVO> children;

}

