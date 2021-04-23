package com.q7w.Service;


import com.q7w.Entity.Goods_sku;

import java.util.List;

/**
 * @author xiaogu
 * @date 2021/4/11 18:55
 **/
public interface SkuService {
    public Goods_sku findbyidorname(int sid);
    public Goods_sku findbyidorname(String name);
    public List<Goods_sku> listall();
    public List<Goods_sku> skuquery(Integer gid);
    public int newsku(Goods_sku goods_sku);
    public int queryskustock(int sid);
    public int skucut(int sid,int count);
    public boolean isexist(int sid);
    public int updatesql(int gid);
    public int delsku(int sid);
    public int delskulist(int gid);
    public byte loadskutoredis(int gid);
    public int opsql(int sid,int op,int num);

}
