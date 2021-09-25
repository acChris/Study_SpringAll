package com.weng.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: acChris
 * @Date: 2021/9/24 20:06
 * @Description:
 */
@Configuration
public class MybatisPlusConfig {

    //    分页插件
    @Bean
    public PaginationInterceptor  myabtisPlusInterceptor(){
        return new PaginationInterceptor();
    }

    // 逻辑删除插件（高版本无须配置）
    /*@Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }*/
}
