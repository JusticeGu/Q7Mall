package com.q7w.Service.impl;

import com.q7w.Dao.BrandDAO;
import com.q7w.Entity.Brand;
import com.q7w.Service.BrandService;
import com.q7w.Service.UserFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author xiaogu
 * @date 2021/3/31 12:15
 **/
@Service
public class BrandServiceimpl implements BrandService {
    @Autowired
    BrandDAO brandDAO;
    @Autowired
    UserFeign userFeign;
    @Override
    public List<Brand> list() {
        return brandDAO.findAll();
    }
    @Override
    public boolean isexistbyid(Integer bid){
        if (brandDAO.findById(bid).isPresent()){return true;}
        return false;
    }

    @Override
    public boolean isexistbyname(String name) {
        if (brandDAO.findByName(name).equals(null)){return false;}
        return true;
    }

    @Override
    public byte addbrand(Brand brand) {
        if(brandDAO.findByName(brand.getName())!=null){return 2;}
        Date now= new Date();
        Long time = now.getTime();
        brand.setCreateBy(userFeign.getusername());
        brand.setCreateTime(time);
        brand.setLastmodifiedBy(userFeign.getusername());
        brand.setUpdateTime(time);
        brandDAO.save(brand);
        return 1;
    }

    @Override
    public byte delbrand(Integer bid) {
        try{
        brandDAO.deleteById(bid);
            return 1;
        }catch (Exception e)
        {
            return 2;
        }

    }

    @Override
    public byte modefybrand(Brand brand) {
        return 0;
    }

    @Override
    public List<Brand> querybrand(String brandname) {
        return null;
    }
}
