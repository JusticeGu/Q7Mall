package com.q7mall.Dao;

import com.q7mall.Entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author xiaogu
 * @date 2021/4/1 19:21
 **/
public interface CategoriesDAO extends JpaRepository<Categories,Integer> {
    Categories findById(int id);
}
