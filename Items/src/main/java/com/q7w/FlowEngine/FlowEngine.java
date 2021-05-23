package com.q7w.FlowEngine;

import cn.hutool.core.util.StrUtil;
import com.alibaba.csp.sentinel.concurrent.NamedThreadFactory;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 引擎执行入口
 * @author xiaogu
 * @date 2021/4/29 12:34
 **/
@Service
public class FlowEngine {
    public void excute(FlowNode flowNode,RunData rundata,Context context) throws Exception{
        Map<String,List<String>> nodeGroup = groupByGroupName(flowNode);

        Map<String,FlowNode.NodeConf> nodeMap = flowNode.getNodeMap();
        for (String groupName:nodeGroup.keySet()){
            boolean needThrowExp = false;

        }

    }
    /**
    * 流程中的参数
    */
    public static class RunData{
        private String paramOne;
        private String paramTwo;
        public String getParamOne() {
            return paramOne;
        }
        public void setParamOne(String paramOne) {
            this.paramOne = paramOne;
        }
        public String getParamTwo() {
            return paramTwo;
        }
        public void setParamTwo(String paramTwo) {
            this.paramTwo = paramTwo;
        }
    }
    private Map<String, List<String>> groupByGroupName(FlowNode flowNode){
        Map<String,List<String>> nodeGroup = Maps.newLinkedHashMap();
        for (String nodekey : flowNode.getNodeList()){
            String groupName = getGroupName(nodekey);
            String nodeName = getNodeName(nodekey);

            if (StrUtil.isBlank(groupName)){
                List<String> nodeNameList = Lists.newArrayList();
                nodeNameList.add(nodeName);
                nodeGroup.put(nodeName,nodeNameList);
            }else {
                List<String> nodeNameList = nodeGroup.get(groupName);
                if (nodeNameList == null){
                    nodeNameList = Lists.newArrayList();
                }
                nodeNameList.add(nodeName);
                nodeGroup.put(nodeName,nodeNameList);
            }
        }
        return nodeGroup;
    }
    private String getGroupName(String  nodekey){
        String[] arr = nodekey.split("_");
        return arr.length == 2 ? arr[0]:null;
    }
    private String getNodeName(String nodeKey){
        String[] arr = nodeKey.split("_");
        return arr.length == 2 ? arr[1] : arr[0];
    }
    public static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5,10,
            60L, TimeUnit.MINUTES,new LinkedBlockingQueue<Runnable>(500), new
            NamedThreadFactory("engine proccessor"),
            new ThreadPoolExecutor.AbortPolicy(){
                @Override
                public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                    //num.incrementAndGet();
                    throw new RejectedExecutionException("Task"+r.toString()+ " rejected from "+executor.toString());
                }

            });

}
