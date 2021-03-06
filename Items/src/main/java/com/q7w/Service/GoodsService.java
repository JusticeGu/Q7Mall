package com.q7w.Service;

import com.q7w.DTO.Product;
import com.q7w.DTO.Spu;
import com.q7w.Entity.Goods;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author xiaogu
 * @date 2020/12/21 18:54
 **/
public interface GoodsService {
    public Goods findbyidorname(int gid);
    public Goods findbyidorname(String name);
    public Page<Goods> list(Pageable pageable);
    public Page<Goods> listall(Pageable pageable);
    public Product iteminfo(int gid);
    public byte addGoods(Goods goods);
    public byte delgoods(int gid);
    public byte modifygoods(int gid);
    public List<Goods> listbyname(String name);
    public List<Goods> listbyid(int id);
    public List<Goods> listbycate(int cid);
    public List<Spu> listall_b();
}
