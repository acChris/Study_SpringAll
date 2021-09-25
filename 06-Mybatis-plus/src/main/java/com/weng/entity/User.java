package com.weng.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: acChris
 * @Date: 2021/9/24 20:49
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value="test_mybatisplus")
public class User {

    @TableId(value="id", type=IdType.AUTO)
    private Long id;
//    @TableField(value="name")
    private String name;
//    @TableField(value="name")
    private Integer age;
//    @TableField(value="name")
    private String email;

    @TableLogic
    private Integer is_deleted;

    public User(Long id, String name, Integer age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }
}
