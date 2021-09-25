package com.weng.config;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: acChris
 * @Date: 2021/9/24 18:43
 * @Description:
 */
public class SpringBootWebMvcConfig implements WebMvcConfigurer {


//    添加 addResourceHandler 可以从磁盘读取数据
    public void addResourceHandler(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/files/**").addResourceLocations("file:D:\\upload\\");
//        所有以 /files/ 开发的请求，都会到后面配置路径下查找资源
        registry.addResourceHandler("/files/**").addResourceLocations("file:/home/project/upload/");
    }
}
