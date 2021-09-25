package com.weng.springboot.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class JdbcController {

    // 自动注入
    @Autowired
    JdbcTemplate jdbcTemplate;

    // 查询所有记录
    @GetMapping("/user/list")
    public List<Map<String, Object>> getList(){
        String sql = "select * from tb_user";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }

    // 插入用户
    @GetMapping("/user/insert")
    public Object insert(String name, String password){
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(password)){
            return false;
        }
        // \" 是转换为“的意思
        String sql = "insert into tb_user(`name`,`password`) value (\"" + name + "\",\"" + password + "\")";
        jdbcTemplate.execute(sql);
        return true;
    }
}
