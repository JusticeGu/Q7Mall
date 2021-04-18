package com.q7w.Controller;

import com.q7w.Service.ImgService;
import com.q7w.common.result.ExceptionMsg;
import com.q7w.common.result.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaogu
 * @date 2021/4/18 19:04
 **/
@RestController
@Api(tags = "图片服务接口")
@RequestMapping("/api/img")
public class ImgController {
    @Autowired
    ImgService imgService;
    @GetMapping("/itemnew10")
    @ApiOperation("最新商品图片TOP10")
    public ResponseData itemnew10(){
        //逻辑
        return new ResponseData(ExceptionMsg.SUCCESS,imgService.newitemtop10());
    }
    @GetMapping("/itemtop10")
    @ApiOperation("最热商品图片TOP10")
    public ResponseData itemhot10(){
        //逻辑
        return new ResponseData(ExceptionMsg.SUCCESS,imgService.newitemtop10());
    }
}
