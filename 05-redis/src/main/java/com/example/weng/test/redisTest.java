package com.example.weng.test;

import redis.clients.jedis.Jedis;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * 功能描述：
 *
 * @Author: acChris
 * @Date: 2021/9/1 19:51
 */
public class redisTest {
    public static void main(String[] args) {
        // 1. 连接到 redis 服务器
        Jedis jedis = new Jedis("127.0.0.1");
        System.out.println("redis 连接成功！");
        // 2. 查看服务是否运行
        System.out.println("" + jedis.ping());
        System.out.println();

        // 3. redis 存储 string实例
        jedis.set("stringTest", "redisStringTest");
        System.out.println("stringTest : \n" + jedis.get("stringTest"));
        System.out.println();

        // 4. redis 存储 list 实例
        jedis.rpush("listTest", "listTest1");
        jedis.rpush("listTest", "listTest2");
        jedis.rpush("listTest", "listTest3");
        List<String> list = jedis.lrange("listTest", 0, 2);
        System.out.println("listTest : ");
        for (int i = 0, len = list.size(); i < len; i++) {
            System.out.print(list.get(i) + "    ");
        }
        System.out.println("\n");

        // 5. redis 存储 set 实例
        jedis.sadd("setTest", "setTest1");
        jedis.sadd("setTest", "setTest2");
        jedis.sadd("setTest", "setTest3");
        Set<String> set = jedis.smembers("setTest");
        Iterator<String> iter = set.iterator();
        System.out.println("setTest : ");
        while (iter.hasNext()){
            System.out.print(iter.next() + "    ");
        }

        // 6. 关闭 redis
        jedis.close();
    }
}
