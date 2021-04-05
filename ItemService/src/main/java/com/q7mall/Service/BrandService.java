package com.q7mall.Service;

import com.q7mall.Entity.Brand;

import java.util.List;

/**
 * @author xiaogu
 * @date 2021/3/31 12:12
 **/
public interface BrandService {
    public List<Brand> list();
    public byte addbrand(Brand brand);
    public byte delbrand(int bid);
    public byte modefybrand(Brand brand);
    public List<Brand> querybrand(String brandname);
}
