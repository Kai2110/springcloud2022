package cn.com.kai.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * ClassName:RedisService
 * Package:cn.com.kai.service
 * Description:
 *
 * @Author:gkr
 * @Date:2022-03-01 23:24
 */
@Service
public class RedisService {

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public  String  setName(String name){
//        redisTemplate.opsForValue().setIfAbsent()

        return  redisTemplate.opsForValue().get("name").toString();
    }


}
