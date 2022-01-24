package com.q7w.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author xiaogu
 * @date 2021/8/9 01:13
 **/
@Data
@Entity
public class StudentClass extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;//班级id
    private String name;//班级名称
    private int type;//班级类型
    private int allownum;//班级限额
    private int num;
    private byte status;//班级状态：0：初始化班级1：未开班，可报名2：未开班，满额 3：已开班4：已结课-1：班级取消
}
