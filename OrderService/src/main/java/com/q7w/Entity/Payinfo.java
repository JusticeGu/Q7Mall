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
 * @date 2021/3/31 16:15
 **/
@Entity
@Table(name = "Payinfo")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Payinfo extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 3033545151355633270L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pid; //流水号
    private Long price;//金额*100
    private Long oid; //订单 ID
    private Long uid;//操作用户id
    private Long adid;//操作人员id
    private int source;//操作来源
    private byte type;//支付类型 1-支付宝 2-wx 3-站内余额 4-卡密 5-人工确认
    private String paycode;//第三方支付流水号 站内余额则同pid
    private byte status;//支付状态 1-支付失败 2-支付成功
    private String content;//支付信息备注

}
