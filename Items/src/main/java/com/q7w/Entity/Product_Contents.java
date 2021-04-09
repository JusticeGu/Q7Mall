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
 * @date 2021/4/1 20:00
 **/
@Entity
@Table(name = "Product_Contents")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
@ApiModel(value="商品描述类",description="商品描述类")
public class Product_Contents {
    private static final long serialVersionUID = 3033545151355633270L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cid;//c-id
    private int pid;
    private String content;
}
