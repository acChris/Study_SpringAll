package com.sprintboot.config.Controller;

import com.sprintboot.config.Bean.BlogProperties;
import com.sprintboot.config.Bean.TestConfigBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    // 1. 自动装配
    @Autowired
    private BlogProperties blogProperties;

    //2. 注入Bean
//    @Autowired
//    private ConfigBean configBean;

    // 3. 测试自定义配置文件test.properties
    @Autowired
    private TestConfigBean testConfigBean;

    // 方法1
//    @RequestMapping("/")
//    String index(){
//        return blogProperties.getName()+"-"+blogProperties.getTitle();
//    }

////     方法2
//    @RequestMapping("/")
//    String index(){
//        return configBean.getName()+configBean.getTitle();
//    }

    // 方法3
    @RequestMapping("/")
    public String index(){
        return testConfigBean.getName() + "--" + testConfigBean.getAge();
    }
}
