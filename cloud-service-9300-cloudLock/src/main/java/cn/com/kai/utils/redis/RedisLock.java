package cn.com.kai.utils.redis;

import cn.hutool.core.util.BooleanUtil;
import cn.hutool.json.JSONUtil;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisLock {

    private final StringRedisTemplate stringRedisTemplate;

    public RedisLock(StringRedisTemplate stringRedisTemplate){
        this.stringRedisTemplate = stringRedisTemplate;
    }

    public <T> boolean tryLock(String lockName, T value, long timeout, TimeUnit unit){
        Boolean result = stringRedisTemplate.opsForValue().setIfAbsent(lockName, JSONUtil.toJsonStr(value), timeout, unit);
        return BooleanUtil.isTrue(result);
    }

    public <T> boolean unlock(String lockName,T value){
        String s = stringRedisTemplate.opsForValue().get(lockName);
        if(s.equals(value)){
            Boolean delete = stringRedisTemplate.delete(lockName);
            return BooleanUtil.isTrue(delete);
        }else{
            return false;
        }
    }



}
