package com.q7w.Service;

import com.q7w.Entity.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author xiaogu
 * @date 2021/3/31 12:12
 **/
public interface BrandService {
    public Page<Brand> list(Pageable pageable);
    public boolean isexistbyid(Integer bid);
    public boolean isexistbyname(String name);
    public byte addbrand(Brand brand);
    public byte delbrand(Integer bid);
    public byte modefybrand(Brand brand);
    public List<Brand> querybrand(String brandname);
    public int updateptnum(int bid,int op,int num);
}
