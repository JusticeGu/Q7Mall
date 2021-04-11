package com.q7w.Dao;

import com.q7w.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author xiaogu
 * @date 2021/4/11 18:24
 **/
public interface OrderDao extends JpaRepository<Order,Integer> {
}
