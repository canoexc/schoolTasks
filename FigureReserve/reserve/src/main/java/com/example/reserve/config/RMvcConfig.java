package com.example.reserve.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class RMvcConfig implements WebMvcConfigurer {
    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
            "classpath:/META-INF/resources/", "classpath:/resources/",
            "classpath:/static/", "classpath:/public/" };

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/signup").setViewName("signup");
        registry.addViewController("/main").setViewName("main");
        registry.addViewController("/admin").setViewName("admin");
        registry.addViewController("/addNew").setViewName("addNew");
        registry.addViewController("/showProduct").setViewName("showProduct");
        registry.addViewController("/bookInfo").setViewName("bookInfo");
        registry.addViewController("/showBook").setViewName("showBook");
        registry.addViewController("/myNotice").setViewName("myNotice");
        registry.addViewController("/profile").setViewName("profile");
        registry.addViewController("/log").setViewName("myOrder");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //super.addInterceptors(registry);
        registry.addInterceptor( new LoginHandlerInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/","/user/signin","/user/check","/signup","/static/**","/webjars/**","/**/*.css", "/**/*.js", "/**/*.png", "/**/*.jpg",
                        "/**/*.jpeg", "/**/*.gif", "/**/fonts/*", "/**/*.svg");
    }
}

