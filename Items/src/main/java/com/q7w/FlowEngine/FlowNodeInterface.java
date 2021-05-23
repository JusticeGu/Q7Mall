package com.q7w.FlowEngine;

import org.apache.poi.ss.formula.functions.T;

/**
 * @author xiaogu
 * @date 2021/4/29 16:12
 **/
public interface FlowNodeInterface {
    T invokeNode(FlowEngine.RunData nodeData,Context context);

    void afterInvoke(FlowEngine.RunData nodeData,Context context);

    String result();
}
