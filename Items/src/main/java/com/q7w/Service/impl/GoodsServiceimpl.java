package com.q7w.Service.impl;

import com.q7w.DTO.Product;
import com.q7w.Dao.BrandDAO;
import com.q7w.Dao.GoodsDAO;
import com.q7w.Entity.Goods;
import com.q7w.Entity.Product_Contents;
import com.q7w.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author xiaogu
 * @date 2021/3/30 15:41
 **/
@Service
public class GoodsServiceimpl implements GoodsService {
    @Autowired
    GoodsDAO goodsDAO;
    @Autowired
    UserFeign userFeign;
    @Autowired
    BrandService brandService;
    @Autowired
    ImgService imgService;
    @Autowired
    SkuService skuService;

    @Override
    public List<Goods> list() {
        return goodsDAO.findAll();
    }

    @Override
    public byte addGoods(Goods goods) {
        if(goodsDAO.findByName(goods.getName())!=null){return 2;}
        if (!brandService.isexistbyid(goods.getBrand().getBid())){return 3;}
        Date now= new Date();
        Long time = now.getTime();
        goods.setCreateBy(userFeign.getusername());
        goods.setCreateTime(time);
        goods.setLastmodifiedBy(userFeign.getusername());
        goods.setUpdateTime(time);
        Product_Contents product_contents = new Product_Contents();
        product_contents.setContent(goods.getSummary().getContent());
        goods.setSummary(product_contents);
        goodsDAO.save(goods);
        return 1;
    }

    @Override
    public Product iteminfo(int gid) {
        Product product = new Product();
        product.setGoodsinfo(goodsDAO.findById(gid));
        product.setGoodimg(imgService.listallbyid(gid));
        product.setGoods_skus(skuService.skuquery(gid));
        return product;
    }

    @Override
    public byte delgoods() {
        //TODO
        return 0;
    }

    @Override
    public byte modifygoods() {
        //TODO
        return 0;
    }

    @Override
    public List<Goods> listbyname(String name) {
        //TODO
        return null;
    }

    @Override
    public List<Goods> listbyid(int id) {
        //TODO
        return null;
    }
}
