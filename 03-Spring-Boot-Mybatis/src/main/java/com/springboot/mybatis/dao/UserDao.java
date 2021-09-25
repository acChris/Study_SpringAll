package com.springboot.mybatis.dao;

import com.springboot.mybatis.entity.User;

import java.util.List;

public interface UserDao {

    // 查询所有用户
    List<User> findAllUsers();

    // 新增用户
    int createUser(User user);

    // 修改用户
    int updateUser(User user);

    // 删除用户
    int deleteUser(Integer id);

    // 通过id获取用户
    User getUserById(Integer id);
}
