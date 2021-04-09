package com.q7w.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author xiaogu
 * @date 2020/12/18 下午2:06
 **/
@Entity
@Table(name = "property_name")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
@ApiModel(value="商品属性名类",description="商品属性名类")
public class Property_name extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 3033545151355633270L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;//属性名
    private int cate_id;//分类ID
    private boolean is_allow_alias;//是否允许别名
    private int filter_type;//分类筛选样式：1->普通；1->颜色
    private int select_type;//属性选择类型：0->唯一；1->单选；2->多选
    private boolean is_input;//属性录入方式：0->手工录入；1->从列表中选取'
    private String input_list;// '可选值列表，以逗号隔开
    private boolean is_key;//是否关键属性
    private boolean is_sale;//是否销售属性
    private boolean is_search;//是否搜索字段
    private byte status;//状态
    private int sort;//排序字段
}
