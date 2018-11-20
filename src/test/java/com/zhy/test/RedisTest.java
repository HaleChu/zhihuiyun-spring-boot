package com.zhy.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * 测试类
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private StringRedisTemplate redisTemplate;

    // 测试redis插入数据
    @Test
    public void redisSaveValue() {
        String name = "jack";
        String password = "123456";
        redisTemplate.opsForValue().set(name, password, 5, TimeUnit.MINUTES);
    }

    // 测试redis读取数据
    @Test
    public void redisGetValue() {
        String name = "jack";
        String password = redisTemplate.opsForValue().get(name);
        System.out.println(password);
    }

    // 测试redis删除数据
    @Test
    public void redisDeleteValue() {
        String name = "jack";
        redisTemplate.delete(name);
    }
}
