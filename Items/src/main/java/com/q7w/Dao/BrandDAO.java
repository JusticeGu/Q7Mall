package com.q7w.Dao;

import com.q7w.Entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author xiaogu
 * @date 2021/3/31 12:15
 **/
public interface BrandDAO extends JpaRepository<Brand,Integer> {
    List<Brand> findAllByName(String brandname);
    Brand findByBid(Integer bid);
    Brand findByName(String brandname);
}
