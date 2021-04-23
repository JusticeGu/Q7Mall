package com.q7w.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author xiaogu
 * @date 2020/12/19 上午11:58
 **/
@Entity
@Table(name = "brand")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
@ApiModel(value="品牌类",description="品牌表")
public class Brand extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 3033545151355633270L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer bid;
    private String name;//品牌名称
    private String logo;
    private String big_pic;//专区大图
    private int b_sort;
    private int product_count;//商品数量
    private boolean varify;//是否名牌
    @JsonIgnore
    @OneToMany(mappedBy = "brand",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    //级联保存、更新、删除、刷新;延迟加载。当删除用户，会级联删除该用户的所有文章
    //拥有mappedBy注解的实体类为关系被维护端
    private List<Goods> goodsList;

}
