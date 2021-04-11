package com.q7w.Service.impl;

import com.q7w.Dao.GoodSkuDAO;
import com.q7w.Entity.Goods_sku;
import com.q7w.Service.SkuService;
import com.q7w.common.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Action;
import java.util.List;

/**
 * @author xiaogu
 * @date 2021/4/11 18:57
 **/
@Service
public class SkuServiceimpl implements SkuService {
    @Autowired
    GoodSkuDAO skudao;
    @Autowired
    RedisService redisService;
    @Override
    public List<Goods_sku> listall() {
        return skudao.findAll();
    }

    @Override
    public boolean isexist(int sid) {
        if (skudao.findById(sid).equals(null)){return false;}
        return true;
    }

    @Override
    public int queryskustock(int sid) {
        Object stock = redisService.get("goods:sku:"+sid);
        if (stock !=null){return (Integer)stock;}
        stock = skudao.findById(sid).getStock();
        if (!isexist(sid)){return -2;}
        redisService.set("goods:sku:"+sid,stock);
        return (Integer)stock;
    }

    @Override
    public int skucut(int sid,int count) {
        int stock = queryskustock(sid);
        if (stock >0){
            redisService.set("goods:sku:"+sid,(Integer)stock-count);
            skudao.findById(sid).setStock((Integer) stock-count);
            return (Integer)stock-count;
        }
        return stock-1;
    }
}
