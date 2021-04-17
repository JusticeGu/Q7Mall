package com.q7w.Dao;

import com.q7w.Entity.Goods_images;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author xiaogu
 * @date 2021/4/13 18:34
 **/
public interface ImgDao extends JpaRepository<Goods_images,Integer> {
    List<Goods_images> findAllByGid(int gid);
}
