package com.baizhi.cache;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;

@Configuration
@Aspect
public class CacheHash {
    @Resource
    RedisTemplate redisTemplate;
    @Resource
    StringRedisTemplate stringRedisTemplate;

    //添加缓存
    @Around("@annotation(com.baizhi.annotation.AddCache)")
    public Object addCache(ProceedingJoinPoint joinPoint) throws Throwable {
        //序列化乱码
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        System.out.println("环绕通知");
        //key/value   类型
        //key：唯一  类的全限定名+方法名+参数(实参)
        //value: 查询方法执行后的结果

        //KEY   Hash<key,value>
        //类的全限定名   Hash<方法名+参数(实参),数据>
        StringBuilder sb = new StringBuilder();
        //获取类的全限定名
        String name = joinPoint.getTarget().getClass().getName();

        //获取方法名
        String methodName = joinPoint.getSignature().getName();
        sb.append(methodName);
        //获取参数
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            sb.append(arg);
        }
        //获取小key
        String key = sb.toString();
        //取出大key
        Boolean aBoolean = redisTemplate.opsForHash().hasKey(name, key);
        //判断：去redis中判断key是否存在，如果存在取出原有的值返回，如果不存在，拿到执行方法结果，进行缓存返回
        HashOperations hashOperations = redisTemplate.opsForHash();
        Object result = null;
        //判断：去redis中判断key是否存在，如果存在取出原有的值返回，如果不存在，拿到执行方法结果，进行缓存返回
        if (aBoolean) {
            result = hashOperations.get(name, key);
        } else {
            result = joinPoint.proceed();
            //加入缓存
            hashOperations.put(name, key, result);
        }
        return result;
    }

    //清空缓存
    @After("@annotation(com.baizhi.annotation.DelCache)")
    public void delCache(JoinPoint joinPoint) {
        //区分各个模块的key值
        //获取类的全限定名
        String className = joinPoint.getTarget().getClass().getName();
        //获取所有的key
        redisTemplate.delete(className);
    }
}
