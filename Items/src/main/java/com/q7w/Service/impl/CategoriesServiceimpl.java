package com.q7w.Service.impl;

import com.q7w.Dao.CategoriesDAO;
import com.q7w.Entity.Categories;
import com.q7w.Service.CategoriesService;
import com.q7w.Service.UserFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author xiaogu
 * @date 2021/4/10 15:09
 **/
@Service
public class CategoriesServiceimpl implements CategoriesService {
    @Autowired
    CategoriesDAO categoriesDAO;
    @Autowired
    UserFeign userFeign;
    @Override
    public List<Categories> list() {
        return categoriesDAO.findAll();
    }

    @Override
    public byte add(Categories categories) {
        if(categoriesDAO.findByName(categories.getName())!=null){
            return 2;}
        Date now= new Date();
        Long time = now.getTime();
        categories.setCreateBy(userFeign.getusername());
        categories.setCreateTime(time);
        categories.setLastmodifiedBy(userFeign.getusername());
        categories.setUpdateTime(time);
        categoriesDAO.save(categories);
        return 1;
    }

    @Override
    public byte del(int id) {
        try{
            categoriesDAO.deleteById(id);
            return 1;
        }catch (Exception e)
        {
            return 2;
        }
    }

    @Override
    public byte modify(Categories categories) {
        return 0;
    }

    @Override
    public Categories querybyname(String name) {
        return null;
    }
}
