package com.q7w.FlowEngine;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 结果缓存
 * @author xiaogu
 * @date 2021/4/29 12:10
 **/
public class Context {
    private Map<String,Object> resultMap = Maps.newHashMap();

    public Map<String,Object> getAdaptorMap(){
        return resultMap;
    }
    public void setAdaptorMap(Map<String,Object> resultMap){
        this.resultMap=resultMap;
    }
}
