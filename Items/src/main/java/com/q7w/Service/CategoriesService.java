package com.q7w.Service;

import com.q7w.Entity.Categories;

import java.util.List;

/**
 * @author xiaogu
 * @date 2021/4/1 19:22
 **/
public interface CategoriesService {
    public List<Categories> list();
    public byte add(Categories categories);
    public byte del(int id);
    public byte modify(Categories categories);
    public Categories querybyname(String name);

}
