package com.q7w.Service;

import com.q7w.Entity.Brand;

import java.util.List;

/**
 * @author xiaogu
 * @date 2021/3/31 12:12
 **/
public interface BrandService {
    public List<Brand> list();
    public boolean isexistbyid(Integer bid);
    public boolean isexistbyname(String name);
    public byte addbrand(Brand brand);
    public byte delbrand(Integer bid);
    public byte modefybrand(Brand brand);
    public List<Brand> querybrand(String brandname);
}
