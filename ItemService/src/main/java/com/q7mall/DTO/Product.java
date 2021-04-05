package com.q7mall.DTO;

import com.q7mall.Entity.Goods;
import com.q7mall.Entity.Goods_images;
import com.q7mall.Entity.Goods_property;
import com.q7mall.Entity.Goods_sku;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Hashtable;
import java.util.List;

/**
 * @author xiaogu
 * @date 2021/4/1 19:28
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Goods goodsinfo;
    private Goods_images goodimg;
    private Hashtable goodsproperty;
    private List<Goods_sku> goods_skus;
}
