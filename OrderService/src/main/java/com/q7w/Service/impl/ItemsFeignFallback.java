package com.q7w.Service.impl;

import com.q7w.Service.ItemsFeign;
import com.q7w.common.result.ExceptionMsg;
import com.q7w.common.result.ResponseData;
import org.springframework.stereotype.Component;

/**
 * @author xiaogu
 * @date 2020/7/29 14:40
 **/
@Component
public class ItemsFeignFallback implements ItemsFeign {
    @Override
    public ResponseData skustockfeign() {
        return new ResponseData(ExceptionMsg.SE_error,"熔断机制启动");
    }
}
