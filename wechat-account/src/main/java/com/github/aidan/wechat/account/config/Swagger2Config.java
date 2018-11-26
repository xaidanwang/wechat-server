package com.github.aidan.wechat.account.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@ConditionalOnExpression("${spring.resources.add-mappings:true}")
public class Swagger2Config {

    @Configuration
    @EnableSwagger2
    static class DocketConfig {
        @Bean
        public Docket activitiesDocket() {
            return new Docket(DocumentationType.SWAGGER_2)
                    .apiInfo(new ApiInfoBuilder()
                            // 接口文档大标题
                            .title("微信账号接口服务")
                            // 接口文档描述
                            .description("获取微信账号接口")
                            // 版本号
                            .version("v1")
                            .build())
                    //最终url前缀（feign的name属性）
//                .pathMapping("/dynamicsV1")
                    .select()
                    // 要扫描的包()
                    .apis(RequestHandlerSelectors.basePackage("com.github.aidan.wechat.account.controller"))
                    // 此处可以添加过滤器
                    .paths(PathSelectors.any())
                    .build();
        }
    }
}
