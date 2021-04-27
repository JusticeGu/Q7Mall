package com.q7w.Service.impl;

import com.q7w.Dao.GoodSkuDAO;
import com.q7w.Entity.Goods;
import com.q7w.Entity.Goods_sku;
import com.q7w.Service.GoodsService;
import com.q7w.Service.SkuService;
import com.q7w.common.exception.GlobalException;
import com.q7w.common.service.RedisService;
import com.q7w.rabbit.SKUReceiver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    GoodsService goodsService;
    @Autowired
    RedisService redisService;
    private static Logger LOGGER = LoggerFactory.getLogger(SkuServiceimpl.class);
    @Override
    public Goods_sku findbyidorname(int sid) {
        Goods_sku sku = skudao.findById(sid);
        if (sku==null){
            throw new GlobalException("805X05","SKU不存在");
        }
        return sku;
    }

    @Override
    public Goods_sku findbyidorname(String name) {
        Goods_sku sku = skudao.findBySkuname(name);
        if (sku==null){
            throw new GlobalException("805X05","SKU不存在");
        }
        return sku;
    }
    public List<Goods_sku> findbyspuif(Integer gid) {
        List<Goods_sku> skuList = skudao.findAllByGoodsid(gid);
        if (skuList==null){
            throw new GlobalException("805X05-1","该商品暂无SKU库存");
        }
        return skuList;
    }
    @Override
    public List<Goods_sku> listall() {
        return skudao.findAll();
    }
    @Override
    public List<Goods_sku> skuquery(Integer gid) {
      return findbyspuif(gid);

    }
    @Override
    public boolean isexist(int sid) {
        if (skudao.findById(sid).equals(null)){return false;}
        return true;
    }

    @Override
    public int newsku(Goods_sku goods_sku) {
        goods_sku.setGoodsid(goodsService.findbyidorname(goods_sku.getGoodsid()).getId());
        skudao.save(goods_sku);
        return 1;
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
    public byte loadskutoredis(int gid) {
        List<Goods_sku> skus = skudao.findAllByGoodsid(gid);
        for(Goods_sku sku:skus){
            redisService.set("goods:sku:"+sku.getId(),sku.getStock(),goodsService.iteminfo(gid).getGoodsinfo().getStoptime());
        }
        return 1;
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

    @Override
    public int updatesql(int gid) {
        return 0;
    }

    @Override
    public int delsku(int sid) {
        skudao.deleteById(sid);
        return 1;
    }

    @Override
    public int delskulist(int gid) {
        skudao.deleteAll(skudao.findAllByGoodsid(gid));
        return 1;
    }

    @Override
    public int opsql(int sid, int op, int num) {
        if(op==1){
            Goods_sku goods_sku = findbyidorname(sid);
            goods_sku.setStock(goods_sku.getStock()-num);
            skudao.save(goods_sku);
            LOGGER.info("核减库存sid{},{}个单位",sid,num);
            return 1;
        }
        else if(op ==2){
            int stock = queryskustock(sid);
            redisService.set("goods:sku:"+sid,(Integer)stock+num);
            LOGGER.info("释放库存{},{}个单位",sid,num);
            return 1;
        }
        return 0;
    }
}
