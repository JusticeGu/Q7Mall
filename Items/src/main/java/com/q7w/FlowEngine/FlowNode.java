package com.q7w.FlowEngine;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Maps;
import org.apache.poi.util.StringUtil;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * 流程节点
 * @author xiaogu
 * @date 2021/4/29 12:14
 **/
public class FlowNode {
    private Map<String,NodeConf> nodeMap= Maps.newLinkedHashMap();

    public void add(String groupName,Class nodeName,NodeConf nodeConf){
        String key = null;
        if (StrUtil.isNotBlank(groupName)){
            key = groupName+"_"+nodeName.getName();
        }else {
            key = nodeName.getName();
        }
        if (nodeMap.containsKey(key)){
            return;
        }
        nodeMap.put(key,nodeConf);
    }
    public void add(Class nodeName,NodeConf nodeConf){
        add(nodeName.getName(),nodeName,nodeConf);
    }
    public void replace(String groupName,Class nodeName,NodeConf nodeConf){
        String key = null;
        if (StrUtil.isNotBlank(groupName)){
            key = groupName+"_"+nodeName.getName();
        }else {
            key = nodeName.getName();
        }
        nodeMap.put(key,nodeConf);
    }
    public void replace(Class nodeName,NodeConf nodeConf){
        replace(nodeName.getName(),nodeName,nodeConf);
    }
    public void remove(String groupName,Class nodeName){
        String key = null;
        if (StrUtil.isNotBlank(groupName)){
            key = groupName+"_"+nodeName.getName();
        }else {
            key = nodeName.getName();
        }
        nodeMap.remove(key);
    }
    public void remove(Class nodeName){
        remove(nodeName.getName(),nodeName);
    }
    public Set<String> getNodeList(){
        return nodeMap.keySet();
    }
    public Map<String,NodeConf>getNodeMap(){
        return nodeMap;
    }
    public void setMap(LinkedHashMap<String,NodeConf> map){
        this.nodeMap = map;
    }

    public static  class NodeConf{
        public NodeConf(){
        }
        public NodeConf(int timeout){
            this.timeout=timeout;
        }
        public int timeout = 100;
        public int getTimeout(){
            return timeout;
        }
        public void setTimeout(int timeout){
            this.timeout = timeout;
        }
    }
}
