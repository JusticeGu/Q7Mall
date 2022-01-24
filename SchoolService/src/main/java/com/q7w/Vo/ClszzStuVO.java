package com.q7w.Vo;

import lombok.Data;

/**
 * @author xiaogu
 * @date 2021/11/20 23:46
 **/
//查看班级学生VO
@Data
public class ClszzStuVO {
    private Long id;//id
    private Long uid;//学生id
    private String name;//学生姓名
    private Long cid;//班级id
}
