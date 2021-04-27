package com.q7w.Service.impl;

import com.q7w.DTO.Product;
import com.q7w.DTO.Spu;
import com.q7w.Dao.GoodsDAO;
import com.q7w.Entity.Brand;
import com.q7w.Entity.Categories;
import com.q7w.Entity.Goods;
import com.q7w.Entity.Product_Contents;
import com.q7w.Service.*;

import com.q7w.common.exception.GlobalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    @Autowired
    CategoriesService categoriesService;
    @Override
    public Goods findbyidorname(int gid) {
        Goods goods = goodsDAO.findById(gid);
        if (goods==null){
            throw new GlobalException("805X02","SPU不存在");
        }
        return goods;
    }

    @Override
    public Goods findbyidorname(String name) {
        Goods goods = goodsDAO.findByName(name);
        if (goods==null){
            try {
                throw new GlobalException("805X02","SPU不存在");
            } catch (GlobalException e) {
                e.printStackTrace();
            }
        }
        return goods;
    }
    @Override
    public Page<Goods> list(Pageable pageable) {
        return goodsDAO.findAllByStatus(1,pageable);
    }

    @Override
    public List<Spu> listall_b() {
        List<Spu> spus = goodsDAO.getAllByStatus();
        return spus;
    }

    @Override
    public Page<Goods> listall(Pageable pageable) {
        return goodsDAO.findAll(pageable);
    }

    @Override
    public byte addGoods(Goods goods) {
        if(goodsDAO.findByName(goods.getName())!=null){return 2;}
        Date now= new Date();
        Long time = now.getTime();
        goods.setCreateBy(userFeign.getusername());
        goods.setCreateTime(time);
        goods.setLastmodifiedBy(userFeign.getusername());
        goods.setUpdateTime(time);
        Product_Contents product_contents = new Product_Contents();
        product_contents.setContent(goods.getSummary().getContent());
        goods.setSummary(product_contents);
        goods.setBrand(brandService.findbybidorname(goods.getBrand().getBid()));
        goods.setCateid(categoriesService.findbyidorname(goods.getCateid().getCid()));
        goodsDAO.save(goods);
        return 1;
    }

    @Override
    public Product iteminfo(int gid) {
        Product product = new Product();
        product.setGoodsinfo(findbyidorname(gid));
        product.setGoodimg(imgService.listallbyid(gid));
        product.setGoods_skus(skuService.skuquery(gid));
        return product;
    }

    @Override
    public byte delgoods(int gid) {
        try{
        goodsDAO.deleteById(gid);
        skuService.delskulist(gid);
        brandService.updateptnum(goodsDAO.findById(gid).getBrand().getBid(),2,1);

        return 1;}catch (Exception e){
            return 2;
        }
    }

    @Override
    public byte modifygoods(int gid) {
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

    @Override
    public List<Goods> listbycate(int cid) {
        Categories categories=categoriesService.findbyidorname(cid);
        List<Goods> goods= categories.getGoodsList();
        List<Categories> cates = categories.getChildren();
        cates.forEach(m -> {
            List<Categories> children = categoriesService.getAllByParentId(m.getCid());
            goods.addAll(m.getGoodsList());
        });
        cates.removeIf(m -> m.getPid() != 0);

        return goods;

    }
}
