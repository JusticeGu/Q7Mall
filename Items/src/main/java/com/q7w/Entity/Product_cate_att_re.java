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
 * @date 2021/4/4 17:14
 **/
@Entity
@Table(name = "product_category_attribute_relation")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
@ApiModel(value="商品分类和属性的关系表",description="商品分类和属性的关系表")
public class Product_cate_att_re extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 3033545151355633270L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;//id
    private int cate_id;//分类id
    private int attribute_id;//属性id

}
