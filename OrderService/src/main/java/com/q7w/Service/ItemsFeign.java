package com.q7w.Service;

import com.q7w.Service.impl.ItemsFeignFallback;
import com.q7w.common.result.ResponseData;
import com.q7w.config.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @author xiaogu
 * @date 2021/4/17 20:06
 **/
@FeignClient(value = "ItemsCoreService",configuration = FeignConfiguration.class,fallback = ItemsFeignFallback.class)
public interface ItemsFeign {
    @GetMapping("/api/sku/skucut")
    int skustockcut(@RequestParam("skuid") int skuid,@RequestParam("num") int num);
}

