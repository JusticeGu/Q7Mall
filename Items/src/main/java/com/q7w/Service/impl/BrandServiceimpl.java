package com.q7w.Service.impl;

import com.q7w.Dao.BrandDAO;
import com.q7w.Entity.Brand;
import com.q7w.Service.BrandService;
import com.q7w.Service.UserFeign;
import com.q7w.common.exception.GlobalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Brand findbybidorname(Integer bid) {
        Brand brand = brandDAO.findByBid(bid);
        if (brand==null){
                throw new GlobalException("805X01","品牌不存在");
        }
        return brand;
    }

    @Override
    public Brand findbybidorname(String name) {
        Brand brand = brandDAO.findByName(name);
        if (brand==null){
            try {
                throw new GlobalException("805X01","品牌不存在");
            } catch (GlobalException e) {
                e.printStackTrace();
            }
        }
        return brand;
    }

    @Override
    public Page<Brand> list(Pageable pageable) {
        return brandDAO.findAll(pageable);
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
        }catch (EmptyResultDataAccessException e)
        {
            return 2;
        }

    }

    @Override
    public byte modefybrand(Brand brand) {
        Brand brandindb = findbybidorname(brand.getBid());
     //   if (brandindb.equals(null)){return 2;}
        brandindb = brand;
        brandDAO.save(brandindb);
        return 1;
    }

    @Override
    public List<Brand> querybrand(String brandname) { return brandDAO.findAllByName(brandname);
    }

    @Override
    public int updateptnum(int bid, int op, int num) {
        Brand brand = brandDAO.findByBid(bid);
        if (op==1){
            brand.setProduct_count(brand.getProduct_count()+num);
        }else if(op == 2){
            brand.setProduct_count(brand.getProduct_count()-num);
        }
        return 1;
    }
}
