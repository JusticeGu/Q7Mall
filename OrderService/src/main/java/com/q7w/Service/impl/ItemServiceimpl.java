package com.q7w.Service.impl;

import com.q7w.Service.ItemService;
import com.q7w.Service.ItemsFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author xiaogu
 * @date 2021/4/19 19:38
 **/
@Service
public class ItemServiceimpl implements ItemService {
    @Autowired
    ItemsFeign itemsFeign;
    @Override
    public int stockquery(int sid) {
        return 0;
    }
}
