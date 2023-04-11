package com.beatdev.api.config;

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

import static com.beatdev.api.util.swagger.OpenApiConstants.CONFIG_CONTACT_EMAIL;
import static com.beatdev.api.util.swagger.OpenApiConstants.CONFIG_CONTACT_NAME;
import static com.beatdev.api.util.swagger.OpenApiConstants.CONFIG_CONTACT_WEBSITE;
import static com.beatdev.api.util.swagger.OpenApiConstants.CONFIG_INFO_DESCRIPTION;
import static com.beatdev.api.util.swagger.OpenApiConstants.CONFIG_INFO_TERMS_OF_SERVICE;
import static com.beatdev.api.util.swagger.OpenApiConstants.CONFIG_INFO_TITLE;
import static com.beatdev.api.util.swagger.OpenApiConstants.CONFIG_INFO_VERSION;

/**
 * Swagger configuration class.
 */
@Configuration
@EnableSwagger2
public class OpenApiConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.beatdev.api.controller"))
                .paths(PathSelectors.regex("/.*"))
                .build()
                .apiInfo(apiInfo());
    }

    /**
     * Swagger information.
     *
     * @return ApiInfo
     */
    private ApiInfo apiInfo() {
         return new ApiInfoBuilder()
                .title(CONFIG_INFO_TITLE)
                .description(CONFIG_INFO_DESCRIPTION)
                .version(CONFIG_INFO_VERSION)
                .termsOfServiceUrl(CONFIG_INFO_TERMS_OF_SERVICE)
                .contact(new Contact(CONFIG_CONTACT_NAME, CONFIG_CONTACT_WEBSITE,
                        CONFIG_CONTACT_EMAIL))
                .license(null)
                .licenseUrl(null).build();
    }
}