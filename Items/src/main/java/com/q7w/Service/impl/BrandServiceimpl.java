package com.q7w.Service.impl;

import com.q7w.Entity.Brand;
import com.q7w.Service.BrandService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xiaogu
 * @date 2021/3/31 12:15
 **/
@Service
public class BrandServiceimpl implements BrandService {
    @Override
    public List<Brand> list() {
        return null;
    }

    @Override
    public byte addbrand(Brand brand) {
        return 0;
    }

    @Override
    public byte delbrand(int bid) {
        return 0;
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
