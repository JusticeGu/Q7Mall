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
 * @date 2020/12/16 17:56
 **/
@Entity
@Table(name = "Goods_sku")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
@ApiModel(value="商品SKU类",description="商品销售单元具体信息")
public class Goods_sku extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 3033545151355633270L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;//sku-id
    private int goods_id;//商品ID--大类id
    private String sp_data;//sku属性-JSON数组
    private int stock;//库存
    private int low_stocl;//预警库存
    private Long price;//sku售价*100
    private String properties;//SKU属性键值对
    private String bar_code;//条码
    private String sku_code;//商品码
    private byte status;//状态

}

