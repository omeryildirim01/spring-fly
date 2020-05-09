package com.yildirimomer.springfly;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by Omer YILDIRIM on 8.05.2020.
 * I-Luxus GmbH.
 * omer@i-luxus.de
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.yildirimomer.springfly"))
                .paths(PathSelectors.regex("/.*"))
                .build().apiInfo(apiEndpointsInfo());
    }
    private ApiInfo apiEndpointsInfo(){
        return  new ApiInfoBuilder().title("Spring Demo Project")
                .description("Spring Fly Project Api Documentations")
                .contact(new Contact("OMER_YILDIRIM","","yildirimomer01@gmail.com"))
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org.licenses/LICENSE-2.0.html")
                .version("1.4.3")
                .build();
    }
}
