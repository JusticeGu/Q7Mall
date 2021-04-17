package com.q7w.Service.impl;

import com.q7w.Dao.ImgDao;
import com.q7w.Entity.Goods_images;
import com.q7w.Service.ImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xiaogu
 * @date 2021/4/17 13:32
 **/
@Service
public class ImgServiceimpl implements ImgService {
    @Autowired
    ImgDao imgDao;
    @Override
    public List<Goods_images> listallbyid(int gid) {
        return imgDao.findAllByGid(gid);
    }

    @Override
    public int uploadimg(int gid, String url,boolean is_master,int pos) {
        Goods_images goods_images = new Goods_images();
        goods_images.setGid(gid);
        goods_images.setLink(url);
        goods_images.set_master(is_master);
        goods_images.setPosition(pos);
        imgDao.save(goods_images);
        return 1;
    }
}
