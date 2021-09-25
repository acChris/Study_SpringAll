package com.weng.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class JDBCController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    // 查询数据库的所有信息
    // 无实体类，如何获取数据？
    @GetMapping("/userList")
    public List<Map<String, Object>> userList(){
        System.out.println("userList");
        String sql = "select * from user";
        List<Map<String, Object>> list_maps = jdbcTemplate.queryForList(sql);
        return list_maps;
    }

//    @GetMapping("/userList2")
//    public String addUser(){
//        String sql = "insert into user.user(id,name,pwd) values(4,'小明','123456')";
//        jdbcTemplate.update(sql);
//        return "update_ok";
//    }

    // 更新user
    @GetMapping("/updateUser/{id}")
    public String updateUser(@PathVariable("id") int id){
        String sql = "update user.user set name = ? ,pwd=? where id = " + id;

        // 封装
        Object[] objects = new Object[2];
        objects[0] = "小明2";
        objects[1] = "sss";
        jdbcTemplate.update(sql, objects);
        return "update_ok";
    }

    // 删除user
    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") int id){
        String sql = "delete from user.user where id = "+ id;
        jdbcTemplate.update(sql, id);
        return "delete_ok";
    }
}
