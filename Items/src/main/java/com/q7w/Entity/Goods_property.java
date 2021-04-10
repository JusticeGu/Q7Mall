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
 * @date 2020/12/18 下午1:56
 **/
@Entity
@Table(name = "goods_property")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
@ApiModel(value="商品属性类",description="关联各个商品SPU的各个属性以及属性值")
public class Goods_property extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 3033545151355633270L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int pid;// 商品大类 id
    private int prop_name_id;//属性名id
    private int prop_value_id;//属性值id

}
