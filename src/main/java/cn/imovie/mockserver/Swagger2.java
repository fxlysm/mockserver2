package cn.imovie.mockserver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Profile({"test","local"})
@ComponentScan(basePackages = "cn")
@Configuration
@EnableSwagger2
public class Swagger2 {


    @Bean
    public Docket createallRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("全部")
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.imovie.mockserver"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket createotherRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("其它")
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.imovie.mockserver.notice"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket createdefaultRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("微信支付")
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.imovie.mockserver.Wechat"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket createAlipayRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("支付宝支付")
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.imovie.mockserver.Alipay"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket createTppRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("淘票票")
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.imovie.mockserver.taopiaopiao"))
                .paths(PathSelectors.any())
                .build();
    }



    private ApiInfo apiInfo() {//http://liuyong:9025/doc.html
        return new ApiInfoBuilder()
                .title("测试桩相关APIs")
                .description("微信公众号支付【支持支付、订单查询、退款、退款查询、关闭订单】、淘票票相关接口【评论除外】")
                .termsOfServiceUrl("http://git.imovie.cn/test/mockserver")
                .contact("liuyong@imovie.cn")
                .version("1.0.1")
                .license("Copyright © 2008 - 2018  朗韬文化传媒（深圳）有限公司 粤ICP备15102573号")
                .licenseUrl("http://git.imovie.cn/test/mockserver")
                .build();
    }

}
