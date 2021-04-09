package com.q7w.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author xiaogu
 * @date 2020/12/19 下午12:00
 **/
@Entity
@Table(name = "categories")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
@ApiModel(value="商品分类",description="商品分类")
public class Categories {
    private static final long serialVersionUID = 3033545151355633270L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int pid;//父类id 0为顶级分类
    private String cate_name;//分类名称
    private int leavel;//分类级别
    private int sort;//排序字段
    private int product_count;//商品数量
    private String unit_name;//商品单位
    private String description;//描述
    private String icon;//图标
    private boolean show_status;//是否显示
}
