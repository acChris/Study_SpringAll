package com.weng;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.weng.entity.User;
import com.weng.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: acChris
 * @Date: 2021/9/24 20:56
 * @Description:
 */
@SpringBootTest
public class MybatisPlusTests {

    @Autowired
    UserMapper userMapper;

    /**
     * 1. select
     *
     *  查询所有对象并返回列表
     */
    @Test
    public void selectList()  {
//        修改查询条件，此处不修改
//        List<User> users = userMapper.selectList(new QueryWrapper<>());
        List<User> users = userMapper.selectList(null);
        /*for (User user : users){
            System.out.println(user);
        }*/
        users.forEach(System.out::println);
    }

    /**
     * 根据 ID 查找
     */
    @Test
    public void selectById() {
        User user = userMapper.selectById(1L);
        System.out.println(user);
    }

    /**
     *  根据 ids 部分查询
     */
    @Test
    public void selectByIds() {
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1L, 2L, 3L));
        users.forEach(System.out::println);
    }

    /**
     * 条件查询
     *
     * 自定义查询
     */
    @Test
    public void selectByMap(){
        HashMap<String, Object> map = new HashMap<String, Object>();

        // 自定义查询
        map.put("name", "Tom");

        List<User> users = userMapper.selectByMap(map);
//       简化版如下一行 users.forEach(user -> System.out.println(user));
        users.forEach(System.out::println);
    }

    /**
     * 分页查询
     */
    @Test
    public void selectByPage(){
        Page<User> page=new Page<>(1,5);
        //current:当前页
        //size：页面大小
        userMapper.selectPage(page,null);
        page.getRecords().forEach(System.out::println);
    }

    /**
     * 使用 QueryWrapper 实现查询功能
     *
     * 条件查询
     * ① 查询 name 不为空、邮箱不为空 且 年龄大于 15 的用户
     */
    @Test
    public void selectByWrapper1(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.
                isNotNull("name")
                .isNotNull("email")
                .ge("age", 15);

        userMapper.selectList(wrapper).forEach(System.out::println);
    }

    /**
     * ② 查询 name 为 Jone 的用户
     */
    @Test
    public void selectByWrapper2(){
        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        wrapper.
                eq("name", "Jone");

        User user = userMapper.selectOne(wrapper);
        System.out.println(user);
    }

    /**
     * ③ 查询 10 - 20 岁的用户
     */
    @Test
    public void selectByWrapper3(){
        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        wrapper.between("age", 18, 20);

        Integer count = userMapper.selectCount(wrapper);

        System.out.println(count);
    }

    /**
     * 模糊查询
     * ① 使用 like + notLike + likeRight
     *
     * 查询名字不包含 J，名字包含 m，email 右匹配有 test的用户
     */
    @Test
    public void selectByWrapper4(){
        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        wrapper
                .notLike("name", "J")
                .like("name", "m")
                .likeRight("email", "test");

        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }

    /**
     * ② 子查询
     *
     * 查询 年龄小于 20 的 id 的用户
     */
    @Test
    public void selectByWrapper5(){
        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        wrapper
                .inSql("id", "select id from test_mybatisplus where age < 20");

//         selectList()、selectObjs()都可以，前者返回列表，后者返回数量
        /*List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);*/

        List<Object> users = userMapper.selectObjs(wrapper);
        users.forEach(System.out::println);
    }

    /**
     * ③ 降升序排序
     */
    @Test
    public void selectByWrapper6(){
        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        // 降序排序
//        wrapper.orderByDesc("id");
        // 升序排序
        wrapper.orderByAsc("id");

        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }

    /**
     * 2. insert
     *
     * 设置对象的所有属性
     */
    @Test
    public void insertAllArgs() {
//        User user = new User(6L, "weng", 22, "weng@baomidou.com");
        User user = new User(7L, "ac", 24, "ac@baomidou.com");
        int i = userMapper.insert(user);
        if (i != 0){
            User user1 = userMapper.selectById(user.getId());
            System.out.println(user1);
        }else{
            System.out.println("插入失败！");
        }

    }

    /**
     * 设置对象的部分属性
     */
    @Test
    public void insertPartArgs(){
        User user = new User();
        user.setAge(21);
        user.setName("ac");
        user.setEmail("ac@baomidou.com");
        int i = userMapper.insert(user);
        if (i != 0){
            System.out.println("插入 " + user + " 成功");
        }else{
            System.out.println("插入失败！");
        }
    }


    /**
     * 3. 更新
     */
    @Test
    public void updateById(){
        User user = new User(6L, "updated_weng", 22, "weng@baomidou.com");
        int i = userMapper.updateById(user);
        if (i != 0){
            System.out.println("更新 " + user + " 成功");
        }else{
            System.out.println("更新 " + user + " 失败");
        }
    }

    /**
     * 4. 删除
     * 根据 ID 删除
     */
    @Test
    public int deleteById(Long id){
//        Long id = 1441409967293759489L;
        int i = userMapper.deleteById(id);
        if (i != 0){
//            System.out.println("删除 id 为 " + (id) + " 的行数据成功");
            return 1;
        }else{
//            System.out.println("删除失败！");
            return 0;
        }
    }

    /**
     * 根据 ID 列表批量删除
     */
    @Test
    public void deleteByIds(){

//        1. 用于测试 反复执行单删除(deleteById()) 的列表
        /*List<Long> ids = new ArrayList<Long>();
        ids.add(1441412176504983553L);
        ids.add(1441412278455947266L);
        ids.add(1441412326132576258L);
        ids.add(1441412414041030658L);*/
        /*for (Long id : ids){
            int i = deleteById(id);
            if (i != 0){
                System.out.println("删除 id 为 " + id + " 成功！");
            }else{
                System.out.println("删除 id 为 " + id + " 失败！");
            }
        }*/

//        2. 正解（BatchIds）
        /*int i = userMapper.deleteBatchIds(Arrays.asList(1L, 2L, 3L, 4L, 5L));
        if (i != 0){
            System.out.println("删除 ids " + " 成功！");
        }else{
            System.out.println("删除 id 为 " + " 失败！");
        }*/

//       3. 正解（Map）
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "Tom");
        int i = userMapper.deleteByMap(map);

        if (i != 0){
            System.out.println("删除 Tom " + " 成功！");
        }else{
            System.out.println("删除 Tom " + " 失败！");
        }
    }

    /**
     *  逻辑删除
     *
     */
    @Test
    public void deleteLog(){
        userMapper.deleteById(5L);

        User user = userMapper.selectById(5L);
        System.out.println(user);
    }


}
