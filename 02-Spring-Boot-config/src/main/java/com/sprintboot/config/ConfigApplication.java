package com.sprintboot.config;

import com.sprintboot.config.Bean.TestConfigBean;
import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
// 启动ConfigBean类的ConfigurationProperties配置类
//@EnableConfigurationProperties({ConfigBean.class})

// 启动TestConfigBean类的ConfigurationProperties配置类
@EnableConfigurationProperties({TestConfigBean.class})

// 使用xml配置（不推荐） ：通过导入路径来引入xml配置文件
//@ImportResource("classpath:some-application.xml")

public class ConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigApplication.class, args);

        // 如果不想项目的配置 被 命令行 修改，如下设置：
//        SpringApplication app = new SpringApplication(Application.class);
//        app.setAddCommandLineProperties(false);
//        app.run(args);
    }

}
