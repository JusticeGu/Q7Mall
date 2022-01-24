package com.q7w.Vo;

import lombok.Data;

import java.util.List;

/**
 * @author xiaogu
 * @date 2021/11/21 16:41
 **/
@Data
public class StuExamVO {
    private Long uid;
    private Long exam;
    private List<Long> courses;
}
