package com.bm.common.interceptor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;


@ConditionalOnProperty(prefix = "auth", name = "enable", havingValue = "true", matchIfMissing = true)
@Configuration
public class SysAdminAppConfigurer implements WebMvcConfigurer {

    @Autowired
    private SysAdminLoginInterceptor sysAdminLoginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> interceptPatterns = new ArrayList<>();
        interceptPatterns.add("/**");

        // swagger-ui
        String[] excludeSwagger = {"/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**"};

        List<String> excludePatterns = new ArrayList<>();
        //错误
        excludePatterns.add("/error/**");
        //登录
        excludePatterns.add("/bm/user/login_username");
        excludePatterns.add("/bm/user/user_register");
        excludePatterns.add("/bm/user/login_mobile");
        excludePatterns.add("/bm/user/code/**");

        //登录
        registry.addInterceptor(sysAdminLoginInterceptor).addPathPatterns(interceptPatterns)
                .excludePathPatterns(excludePatterns)
                .excludePathPatterns(excludeSwagger);
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}
