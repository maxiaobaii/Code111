package com.baizhi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @ClassNmae: testYxRedis
 * @Author: yddm
 * @DateTime: 2020/9/7 19:50
 * @Description: TODO
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class testYxRedis {
    @Resource
    RedisTemplate redisTemplate;

    @Test
    public void test1() {
        String nmae = (String) redisTemplate.opsForValue().get("nmae");
        System.out.println("nmae = " + nmae);
    }
}
