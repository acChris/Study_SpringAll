package com.weng.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weng.entity.User;

/**
 * @Author: acChris
 * @Date: 2021/9/24 20:50
 * @Description: 继承 BaseMapper 后，省去了xml文件的编写
 * BaseMapper里面有一些常用的 CURD 方法
 */

public interface UserMapper extends BaseMapper<User> {

}
