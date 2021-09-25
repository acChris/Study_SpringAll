package com.springboot.mybatis.controller;

import com.springboot.mybatis.dao.UserDao;
import com.springboot.mybatis.entity.User;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class MybatisController {

    @Resource
    UserDao userdao;

    // 查询所有记录
    @GetMapping("/user/list")
    public List<User> queryAll(){
        return userdao.findAllUsers();
    }

    // 新增一条记录
    @GetMapping("/user/add")
    public Boolean add(String name, String password){
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(password)){
            return false;
        }
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        return userdao.createUser(user) > 0;
    }

    // 修改记录
    @GetMapping("/user/edit")
    public Boolean update(Integer id, String name, String password){
        if (id == null || id < 1 || StringUtils.isEmpty(name) || StringUtils.isEmpty(password)){
            return false;
        }
        User user = userdao.getUserById(id);
        user.setName(name);
        user.setPassword(password);
        return userdao.updateUser(user) > 0;
    }

    // 删除记录
    @GetMapping("/user/delete")
    public Boolean delete(Integer id){
        if (id == null || id < 1){
            return false;
        }
        return userdao.deleteUser(id) > 0;
    }
}
