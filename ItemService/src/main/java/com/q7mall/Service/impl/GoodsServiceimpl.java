package com.q7mall.Service.impl;

import com.q7mall.Dao.GoodsDAO;
import com.q7mall.Entity.Goods;
import com.q7mall.Service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xiaogu
 * @date 2021/3/30 15:41
 **/
@Service
public class GoodsServiceimpl implements GoodsService {
    @Autowired
    GoodsDAO goodsDAO;

    @Override
    public List<Goods> list() {
        return goodsDAO.findAll();
    }

    @Override
    public byte addGoods() {
        return 0;
    }

    @Override
    public byte delgoods() {
        return 0;
    }

    @Override
    public byte modifygoods() {
        return 0;
    }

    @Override
    public List<Goods> listbyname(String name) {
        return null;
    }

    @Override
    public List<Goods> listbyid(int id) {
        return null;
    }
}
