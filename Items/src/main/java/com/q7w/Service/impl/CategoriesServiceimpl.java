package com.q7w.Service.impl;

import com.q7w.Dao.CategoriesDAO;
import com.q7w.Entity.Categories;
import com.q7w.Entity.Goods;
import com.q7w.Service.CategoriesService;
import com.q7w.Service.UserFeign;
import com.q7w.common.exception.GlobalException;
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
    public Categories findbyidorname(int cid) {
        Categories categories = categoriesDAO.findById(cid);
        if (categories==null){
                throw new GlobalException("805X03","分类不存在");
        }
        return categories;
    }

    @Override
    public Categories findbyidorname(String name) {
        Categories categories = categoriesDAO.findByName(name);
        if (categories==null){
            if (categories==null){
                throw new GlobalException("805X03","分类不存在");
            }
        }
        return categories;
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
        Categories categoriesindb = findbyidorname(categories.getCid());
        categoriesindb = categories;
        categoriesDAO.save(categoriesindb);
        return 1;
    }

    @Override
    public Categories querybyname(String name) {
        return findbyidorname(name);
    }
}
