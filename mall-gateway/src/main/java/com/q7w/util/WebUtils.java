package com.q7w.util;


import cn.hutool.json.JSONUtil;
import com.q7w.common.result.ExceptionMsg;
import com.q7w.common.result.ResponseData;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;

import java.nio.charset.Charset;

/**
 * @author xiaogu
 * @date 2021/5/3 12:46
 **/

public class WebUtils {

    public static Mono writeFailedToResponse(ServerHttpResponse response,ExceptionMsg msg,String info){
        response.setStatusCode(HttpStatus.OK);
        response.getHeaders().set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        response.getHeaders().set("Access-Control-Allow-Origin", "*");
        response.getHeaders().set("Cache-Control", "no-cache");
        ResponseData res = new ResponseData(msg,info);
        //     String body= JSONUtil.toJsonStr(CommonResult.unauthorized(e.getMessage()));
        String body= JSONUtil.toJsonStr(res);
        DataBuffer buffer = response.bufferFactory().wrap(body.getBytes(Charset.forName("UTF-8")));
        return response.writeWith(Mono.just(buffer))
                .doOnError(error -> DataBufferUtils.release(buffer));
    }

}