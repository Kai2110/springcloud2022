package cn.com.kai.utils.redis;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@Slf4j
@Component
public class RedisCacheClient {

    private final StringRedisTemplate stringRedisTemplate;

    private RedisLock redisLock;

    private final static  Long CACHE_NULL_TTL = 1l;

    private static final ExecutorService CACHE_REBUILD_EXECUTOR= Executors.newFixedThreadPool(10);

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
    private void set(String key, Object value, long timeout, TimeUnit unit,long random){
        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(value),timeout+random,unit);
    }

    /**
     * 根据指定的key查询缓存，并反序列化为指定类型，并且设置逻辑过期时间
     */
    private void setWithLogicalExpire(String key, Object value, Long timeout, TimeUnit unit){
        //设置逻辑过期时间
        RedisData redisData = new RedisData(value, LocalDateTime.now().plusSeconds(unit.toSeconds(timeout)));
        //写入Redis缓存
        stringRedisTemplate.opsForValue().set(key,JSONUtil.toJsonStr(redisData));
    }


    /**
     * 缓存穿透+缓存雪崩
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
     * 缓存击穿：热点key问题
     *  将任意对象序列化为json缓存到redis的String类型中，并通过设置过期时间，处理缓存击穿问题（热点key问题）
     */
    public <R,T> R setWithLogicalExpire(String keyPrefix, T id, Class<R> clazz,Function<T,R> fallback,Long timeout, TimeUnit unit){
        String key = keyPrefix+":"+id;
        //1.从redis中查询缓存
        String json = stringRedisTemplate.opsForValue().get(key);

        //2.判断数据不存在
        if(StrUtil.isBlank(json)){
            return null;
        }

        RedisData redisData = JSONUtil.toBean(json, RedisData.class);
        R r = JSONUtil.toBean((JSONObject) redisData.getData(), clazz);
        //判断是否逻辑过期
        LocalDateTime expireTime = redisData.getExpireTime();
        if(expireTime.isAfter(LocalDateTime.now())){
            return r;//未过期
        }
        //已经过期：获取互斥锁，重构缓存
        boolean isLock = redisLock.tryLock(key, Thread.currentThread().getName(), 300, TimeUnit.SECONDS);
        if (isLock){
            CACHE_REBUILD_EXECUTOR.submit(()->{
                try {
                    //查询数据库
                    R r1 = fallback.apply(id);
                    //写入数据库（带逻辑时间）
                    this.setWithLogicalExpire(keyPrefix,r1,timeout,unit);
                }catch (Exception e){
                    throw new RuntimeException(e);
                }finally {
                    //释放锁
                    redisLock.unlock(key,Thread.currentThread().getName());
                }
            });
        }
        return r;
    }
}
