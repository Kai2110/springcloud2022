package cn.com.kai.utils.redis;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@Slf4j
@Component
public class RedisCacheClient {

    private final StringRedisTemplate stringRedisTemplate;

    private final static  Long CACHE_NULL_TTL = 1l;

    public RedisCacheClient(StringRedisTemplate stringRedisTemplate){
        this.stringRedisTemplate = stringRedisTemplate;
    }

    /**
     * 将任意对象序列化为json，然后缓存到redis的String类型中，并且设置TTL过期时间
     * @param key       缓存的key
     * @param value     缓存的value，需要序列化的对象
     * @param timeout   过期时间
     * @param unit      时间单位
     * @param random    随机数，防止缓存穿透，根据过期时间和其时间生产一个随机数
     */
    public void set(String key, Object value, long timeout, TimeUnit unit,long random){
        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(value),timeout+random,unit);
    }


    /**
     * 缓存穿透+缓存雪崩=缓存空值+过期时间+
     * 根据指定的key查询缓存，并反序列化为指定类型，利用缓存空值的方式解决缓存穿透问题
     */
    public <R,T> R queryWithPassThrough(String keyPrefix, T id, Class<R> clazz, Function<T,R> dbFallback, long timeout, TimeUnit unit, long random) {
        String key = keyPrefix+":"+id;
        //1.从Redis中查询缓存
        String json = stringRedisTemplate.opsForValue().get(key);

        //2.判断缓存中是否存在：存在直接返回；不存在可能为空对象，也可能为null
        if(StrUtil.isNotBlank(json)){
            return JSONUtil.toBean(json, clazz);
        }

        //3.判断缓存是否为空对象：若为空对象（防止缓存穿透），直接返回；否则从数据库中查询
        if (json != null){
            return null;
        }

        //4.从数据库中查询，并根据查询进行缓存，若数据库中不存在，则缓存空对象防止缓存穿透;
        R r = dbFallback.apply(id);
        if(r == null){
            stringRedisTemplate.opsForValue().set(key,"",CACHE_NULL_TTL,TimeUnit.MINUTES);
        }else{
            this.set(key,r,timeout,unit,random);
        }
        return r;
    }

    /**
     *  将任意对象序列化为json缓存到redis的String类型中，并通过设置过期时间，处理缓存击穿问题（热点key问题）
     */
    public void setWithLogicalExpire(){

    }

    /**
     * 根据指定的key查询缓存，并反序列化为指定类型，并利用逻辑过期解决缓存击穿问题
     */
    public void setWithLogicalExpire(String key, Object value, Long timeout, TimeUnit unit){
        //1.设置逻辑过期时间
        RedisData redisData = new RedisData(value, LocalDateTime.now().plusSeconds(unit.toSeconds(timeout)));
        //2.写入Redis缓存
        stringRedisTemplate.opsForValue().set(key,JSONUtil.toJsonStr(redisData));
    }
}
