package cn.com.kai.service.impl;

import cn.com.kai.domain.Shop;
import cn.com.kai.service.ShopService;
import cn.com.kai.utils.redis.RedisCacheClient;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisCacheClient redisCacheClient;

    @Override
    public Shop selectById(Long id) {
        String key = "cache:shop:"+id;
        return redisCacheClient.queryWithPassThrough("cache:shop",id,Shop.class,shopId-> this.queryById(shopId),300, TimeUnit.SECONDS, RandomUtil.randomInt(1,5));
    }

    public Shop queryById(long id){
        System.out.println("***********开始查询数据库***********");
        Shop shop = null;
        if(id == 1){
            shop = new Shop(1l,"shop01");
        }else{
            id = Long.valueOf(LocalDateTimeUtil.now().getMinute()+"");
            shop = new Shop(id,"shop"+id);
        }
        System.out.println("***********结束查询数据库***********");
        return shop;
    }

    @Override
    public boolean update(Shop shop) {
        //1.更新数据库
        // todo 实现业务逻辑的更新
        //2.删除缓存
        stringRedisTemplate.delete( "cache:shop:"+shop.getId());
        return false;
    }
}
