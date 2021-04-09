package com.q7w.config;


import com.q7w.common.config.BaseSwaggerConfig;
import com.q7w.common.domain.SwaggerProperties;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger API文档相关配置
 * Created by macro on 2018/4/26.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {

    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .apiBasePackage("com.q7w.Controller")
                .title("Q7商城商品服务")
                .description("Q7商城-商品服务接口文档")
                .contactName("q7w")
                .version("1.0")
                .enableSecurity(true)
                .build();
    }
}
