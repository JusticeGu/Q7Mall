package com.q7mall.Entity;

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
 * @date 2020/8/5 22:53
 **/
@Entity
@Table(name = "Items")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
@ApiModel(value="商品类",description="商品大致信息，大类，不含SKU")
public class Goods extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 3033545151355633270L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String goods_name;
    private int brand_id;//品牌id
    private int cate_id;//分类id
    private float original;//原价
    private String tags;//标签
    private String summary;//商品描述
    private int cid;//商品内容id
    private boolean is_sale;//状态 1-是 0-否
    private boolean is_seccut;//秒杀参数 0-非秒杀
    private int busstype;//交易类型 1-正常交易 2-抽签 3-队列人工审核交易 4-其他
    private String saletype;//保障服务类别
    private int type;//1-实物商品 2-虚拟商品 3-虚拟卡 4-在线课程 5-第三方
    private Long buytime;//开始上架时间

    

}
