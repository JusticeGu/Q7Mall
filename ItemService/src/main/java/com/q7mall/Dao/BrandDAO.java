package com.q7mall.Dao;

import com.q7mall.Entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author xiaogu
 * @date 2021/3/31 12:15
 **/
public interface BrandDAO extends JpaRepository<Brand,Integer> {
    Brand findByBid(int bid);
}
