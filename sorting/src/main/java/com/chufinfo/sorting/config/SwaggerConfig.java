package com.chufinfo.sorting.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/swagger/**").addResourceLocations("classpath:/static/swagger/");
    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
                // 加了ApiOperation注解的类，才生成接口文档
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                // 包下的类，才生成接口文档
                .paths(PathSelectors.any()).build().securitySchemes(security());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("楚风接口文档").description("楚风接口文档APIs").version("1.0").build();
    }

    private List<ApiKey> security() {
        ApiKey apiKey = new ApiKey("token", "token", "header");
        List<ApiKey> list = new ArrayList<>();
        list.add(apiKey);
        return list;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void addCorsMappings(CorsRegistry arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void addFormatters(FormatterRegistry arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void addInterceptors(InterceptorRegistry arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void addViewControllers(ViewControllerRegistry arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void configurePathMatch(PathMatchConfigurer arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public MessageCodesResolver getMessageCodesResolver() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Validator getValidator() {
        // TODO Auto-generated method stub
        return null;
    }
}
