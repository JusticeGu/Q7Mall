package com.q7w.Service;

import com.q7w.Entity.Goods_images;

import java.util.List;

/**
 * @author xiaogu
 * @date 2021/4/13 18:36
 **/
public interface ImgService {
    public List<Goods_images> listallbyid(int gid);
    public List<Goods_images> newitemtop10();
    public int uploadimg(int gid,String url,boolean is_master,int pos);

}
