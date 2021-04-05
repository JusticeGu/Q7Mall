package com.q7mall.Dao;

import com.q7mall.Entity.Goods_sku;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author xiaogu
 * @date 2021/4/1 19:50
 **/
public interface GoodSkuDAO extends JpaRepository<Goods_sku,Integer> {
}
