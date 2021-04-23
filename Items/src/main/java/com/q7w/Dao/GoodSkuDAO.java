package com.q7w.Dao;

import com.q7w.Entity.Goods_sku;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author xiaogu
 * @date 2021/4/1 19:50
 **/
public interface GoodSkuDAO extends JpaRepository<Goods_sku,Integer> {
    Goods_sku findById(int id);
    Goods_sku findBySkuname(String name);
    List<Goods_sku> findAllByGoodsid(int gid);

}
