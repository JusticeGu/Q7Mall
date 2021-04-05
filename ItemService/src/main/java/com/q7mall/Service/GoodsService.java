package com.q7mall.Service;

import com.q7mall.Entity.Goods;

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
