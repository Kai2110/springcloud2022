package cn.com.kai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableDiscoveryClient
public class CloudLockApplication {
    public static void main(String[] args) {
        SpringApplication.run(CloudLockApplication.class,args);
    }

    /**
     * Springboot集成Redis的k开发步骤：
     * step01：引入Redis依赖
     * step02：配置Redis连接池信息
     * step03：注入RedisTemplate | StringRedisTemplate调用API即可
     * ——opsForValue  操作String类型数据
     * ——opsForHash   操作Hash类型数据
     * ——opsForList   操作List类型数据
     * ——opsForSet    操作Set类型数据
     * ——opsForZSet   操作ZSet类型数据
     * step04：自定义key-value序列化
     * ——使用RedisTemplate时，默认使用的是JDK序列化器，可读性差，因此需要自定义序列化器
     */






}
