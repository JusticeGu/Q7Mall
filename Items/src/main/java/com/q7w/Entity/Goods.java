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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private float original;//原价
    private String tags;//标签
    private boolean is_sale;//状态 1-是 0-否
    private boolean is_seccut;//秒杀参数 0-非秒杀
    private int busstype;//交易类型 1-正常交易 2-抽签 3-队列人工审核交易 4-其他
    private String saletype;//保障服务类别
    private int type;//1-实物商品 2-虚拟商品 3-虚拟卡 4-在线课程 5-第三方
    private Long buytime;//开始上架时间
    @OneToOne(cascade=CascadeType.ALL)//People是关系的维护端，当删除 people，会级联删除 address
    @JoinColumn(name = "Product_Contents", referencedColumnName = "cid")//people中的address_id字段参考address表中的id字段
    private Product_Contents summary;//商品描述
    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)//可选属性optional=false,表示author不能为空。删除文章，不影响用户
    @JoinColumn(name="bid")//设置在ban表中的关联字段(外键)
    private Brand brand;//品牌
    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)//可选属性optional=false,表示author不能为空。删除文章，不影响用户
    @JoinColumn(name="cid")//设置在cate表中的关联字段(外键)
    private Categories cateid;//品牌

}
