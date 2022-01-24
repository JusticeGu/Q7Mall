package com.q7w.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author xiaogu
 * @date 2021/8/9 14:59
 **/
@Data
@Entity
public class ClszzStu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;//id
    private Long uid;//学生id
    private Long cid;//班级id
}
