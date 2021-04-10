package com.q7w.Dao;

import com.q7w.Entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author xiaogu
 * @date 2021/4/1 19:21
 **/
public interface CategoriesDAO extends JpaRepository<Categories,Integer> {
    Categories findById(int id);
    Categories findByName(String Catename);
    List<Categories> findAllByName(String Catename);
}
