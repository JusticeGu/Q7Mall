package com.q7w.Service.impl;

import com.q7w.Service.ItemsFeign;
import com.q7w.common.result.ExceptionMsg;
import com.q7w.common.result.ResponseData;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author xiaogu
 * @date 2020/7/29 14:40
 **/
@Component
public class ItemsFeignFallback implements ItemsFeign {
    @Override
    public int skustockcut(@RequestParam("skuid") int skuid,@RequestParam("num") int num) { return -3; }
}
