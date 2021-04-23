package com.q7w.Service;

import com.q7w.DTO.Product;
import com.q7w.Entity.Goods;

import java.util.List;

/**
 * @author xiaogu
 * @date 2020/12/21 18:54
 **/
public interface GoodsService {
    public Goods findbyidorname(int gid);
    public Goods findbyidorname(String name);
    public List<Goods> list();
    public Product iteminfo(int gid);
    public byte addGoods(Goods goods);
    public byte delgoods(int gid);
    public byte modifygoods(int gid);
    public List<Goods> listbyname(String name);
    public List<Goods> listbyid(int id);
}
