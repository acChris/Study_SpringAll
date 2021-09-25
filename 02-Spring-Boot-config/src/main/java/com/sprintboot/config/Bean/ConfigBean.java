package com.sprintboot.config.Bean;

import org.springframework.boot.context.properties.ConfigurationProperties;

//法2：@ConfigurationProperties：
//在properties里找到相应前缀的所有值，自动注入

@ConfigurationProperties(prefix = "mrbird.blog")
public class ConfigBean {

    private String name;
    private String title;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
