package com.q7w.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author xiaogu
 * @date 2021/3/31 16:14
 **/
@Entity
@Table(name = "Orderinfo")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Order extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 3033545151355633270L;
    @Id
    private Long oid; //订单号
    private Integer skuid; //SKU ID
    private Long uid;//用户id
    private int odsource;//订单来源
    private byte type;//订单类型 1-实物 2-站内虚拟 3-站外商品 4-卡密
    private int busstype;//交易类型 1-正常交易 2-抽签 3-队列人工审核交易 4-其他
    private Long price;//订单金额*100
    private Long oriprice;//原价*100
    private String cpcode;//优惠券码
    private boolean transis;//是否包邮
    private String transcode;//物流码
    private int status;//订单状态 -3-支付失败/取消订单 -2抽选未选中/抢购失败 -1官方撤销 0-已创建未支付 1-支付成功-正在处理 2-支付成功-未发货 3-正在运输 4-已完成(结单) 5-已评价  6-已退款
    private Long payid;//支付信息id
    private Long nid;//站外订单号
    private String content;//订单备注

}
