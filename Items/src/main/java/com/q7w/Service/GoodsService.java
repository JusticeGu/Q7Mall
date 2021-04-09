package com.q7w.Service;

import com.q7w.Entity.Goods;

import java.util.List;

/**
 * @author xiaogu
 * @date 2020/12/21 18:54
 **/
public interface GoodsService {
    public List<Goods> list();
    public byte addGoods();
    public byte delgoods();
    public byte modifygoods();
    public List<Goods> listbyname(String name);
    public List<Goods> listbyid(int id);
}