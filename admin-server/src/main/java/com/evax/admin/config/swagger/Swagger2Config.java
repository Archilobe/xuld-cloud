package com.evax.admin.config.swagger;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@EnableSwagger2
@Configuration
public class Swagger2Config {
    @Bean
    public Docket customDocket() {
        ParameterBuilder ticketPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        ticketPar.name("token").description("token")
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(false);
        //根据每个方法名也知道当前方法在设置什么参数
        pars.add(ticketPar.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .genericModelSubstitutes(DeferredResult.class)
                .globalOperationParameters(pars)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(false)
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .build()
                .apiInfo(apiInfo());
    }


    private ApiInfo apiInfo() {
        Contact contact = new Contact("皇家机电", "http://45.40.196.228/", "");
        return new ApiInfoBuilder()
                .title("系统管理接口")
                .description("接口描述")
                .contact(contact)
                .version("1.0.0")
                .build();
    }
}
