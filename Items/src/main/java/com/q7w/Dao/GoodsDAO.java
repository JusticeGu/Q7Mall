package com.q7w.Dao;

import com.q7w.Entity.Brand;
import com.q7w.Entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author xiaogu
 * @date 2021/3/30 15:31
 **/
public interface GoodsDAO extends JpaRepository<Goods,Integer> {
    Goods findById(int id);
    Goods findByName(String name);
    List<Goods> findAllByNameAndStatus(String name, int status);
    List<Goods> findAllByStatus(int status);
    List<Goods> findAllBySeccut(boolean seccut);
    List<Goods> findAllByBrand(Brand brand);
    List<Goods> findAllByBrandAndStatus(Brand brand,int status);



}
