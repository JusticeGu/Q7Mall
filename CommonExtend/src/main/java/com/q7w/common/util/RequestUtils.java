package com.q7w.common.util;


import com.q7w.common.constant.AuthConstant;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
/**
 * @author xiaogu
 * @date 2021/5/20 19:26
 **/
@Slf4j
public class RequestUtils {


    public static HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }

    public static JSONObject getJwtPayload() {
        String jwtPayload = getRequest().getHeader(AuthConstant.USER_TOKEN_HEADER);
        JSONObject jsonObject = JSONUtil.parseObj(jwtPayload);
        return jsonObject;
    }

    public static Long getUserId() {
        Long id = getJwtPayload().getLong(AuthConstant.USER_ID_KEY);
        return id;
    }


    public static String getUsername() {
        String username = getJwtPayload().getStr(AuthConstant.USER_NAME_KEY);
        return username;
    }

    /**
     * 获取JWT的载体中的clientId
     *
     * @return
     */
    public static String getClientId() {
        String clientId = getJwtPayload().getStr(AuthConstant.CLIENT_ID_KEY);
        return clientId;
    }

    /**
     * 获取登录认证的客户端ID
     * <p>
     * 兼容两种方式获取Oauth2客户端信息（client_id、client_secret）
     * 方式一：client_id、client_secret放在请求路径中
     * 方式二：放在请求头（Request Headers）中的Authorization字段，且经过加密，例如 Basic Y2xpZW50OnNlY3JldA== 明文等于 client:secret
     *
     * @return
     */
    @SneakyThrows
    public static String getAuthClientId() {
        String clientId;

        HttpServletRequest request = getRequest();

        // 从请求路径中获取
        clientId = request.getParameter(AuthConstant.CLIENT_ID_KEY);
        if (StrUtil.isNotBlank(clientId)) {
            return clientId;
        }

        // 从请求头获取
        String basic = request.getHeader(AuthConstant.JWT_TOKEN_HEADER);
        basic = request.getHeader(AuthConstant.BASIC_PREFIX);
        if (StrUtil.isNotBlank(basic) && basic.startsWith(AuthConstant.BASIC_PREFIX)) {
            basic = basic.replace(AuthConstant.BASIC_PREFIX, Strings.EMPTY);
            String basicPlainText = new String(new BASE64Decoder().decodeBuffer(basic), "UTF-8");
            clientId = basicPlainText.split(":")[0]; //client:secret
        }
        return clientId;
    }


    public static List getRoleIds() {
        List<String> list = getJwtPayload().get(AuthConstant.AUTHORITY_CLAIM_NAME, List.class);
        List rid = new ArrayList<>();
        //List<Long> authorities = list.stream().map(Long::valueOf).collect(Collectors.toList());
        list.forEach((rp) -> {
         rid.add(Integer.parseInt(rp.split("_")[0]));
        });
        return rid;
    }
}
