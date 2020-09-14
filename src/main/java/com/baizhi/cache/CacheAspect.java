package com.baizhi.cache;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;
import java.util.Set;

@Configuration
@Aspect
public class CacheAspect {
    @Resource
    RedisTemplate redisTemplate;
    @Resource
    StringRedisTemplate stringRedisTemplate;

    //添加缓存
    // @Around("execution(* com.baizhi.service.impl.*.query*(..))")
    public Object addCache(ProceedingJoinPoint joinPoint) throws Throwable {
        //序列化乱码
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        System.out.println("环绕通知");
        //key/value   类型
        //key：唯一  类的全限定名+方法名+参数(实参)
        //value: 查询方法执行后的结果
        StringBuilder sb = new StringBuilder();
        //获取类的全限定名
        String name = joinPoint.getTarget().getClass().getName();
        sb.append(name);
        //获取方法名
        String methodName = joinPoint.getSignature().getName();
        sb.append(methodName);
        //获取参数
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            sb.append(arg);
        }
        //接收key
        String key = sb.toString();
        //取出key
        Boolean aBoolean = stringRedisTemplate.hasKey(key);
        ValueOperations valueOperations = redisTemplate.opsForValue();
        Object result = null;
        //判断：去redis中判断key是否存在，如果存在取出原有的值返回，如果不存在，拿到执行方法结果，进行缓存返回
        if (aBoolean) {
            result = valueOperations.get(key);
        } else {
            result = joinPoint.proceed();
            //加入缓存
            valueOperations.set(key, result);
        }
        return result;
    }

    //清空缓存
    //@After("execution(* com.baizhi.service.impl.*.*(..))&&!execution(* com.baizhi.service.impl.*.query*(..))")
    public void delCache(JoinPoint joinPoint) {
        //区分各个模块的key值
        //获取类的全限定名
        String className = joinPoint.getTarget().getClass().getName();
        //获取所有的key
        Set<String> keys = stringRedisTemplate.keys("*");
        for (String key : keys) {
            if (key.contains(className)) {
                System.out.println(key);
                stringRedisTemplate.delete(key);
            }
        }
    }
}
