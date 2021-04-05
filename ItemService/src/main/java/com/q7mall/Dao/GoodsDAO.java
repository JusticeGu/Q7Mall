package com.q7mall.Dao;

import com.q7mall.Entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author xiaogu
 * @date 2021/3/30 15:31
 **/
public interface GoodsDAO extends JpaRepository<Goods,Integer> {
    Goods findById(int id);

}
