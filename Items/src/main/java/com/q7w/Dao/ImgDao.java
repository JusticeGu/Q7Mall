package com.q7w.Dao;

import com.q7w.Entity.Goods;
import com.q7w.Entity.Goods_images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author xiaogu
 * @date 2021/4/13 18:34
 **/
public interface ImgDao extends JpaRepository<Goods_images,Integer> {
    List<Goods_images> findAllByGid(int gid);
    @Query(nativeQuery =true,value = "select * from goods_images order by create_time desc limit 10")
    List<Goods_images> least10item();
}
