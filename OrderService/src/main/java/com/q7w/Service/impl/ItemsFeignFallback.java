package com.q7w.Service.impl;

import com.q7w.Service.ItemsFeign;
import com.q7w.common.result.ExceptionMsg;
import com.q7w.common.result.ResponseData;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaogu
 * @date 2020/7/29 14:40
 **/
@Component
public class ItemsFeignFallback implements ItemsFeign {
    @Override
    public int skustockcut(@RequestParam("skuid") int skuid,@RequestParam("num") int num) { return -3; }

    @Override
    public Map  skutest(Integer sid) {
        Map map = new HashMap();
        map.put("key","测试失败-熔断");
        //逻辑
        return map;
    }
}
