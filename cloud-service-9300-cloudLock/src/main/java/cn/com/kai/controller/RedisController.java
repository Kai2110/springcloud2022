package cn.com.kai.controller;

import cn.com.kai.domain.Shop;
import cn.com.kai.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * ClassName:RedisController
 * Package:cn.com.kai.controller
 * Description:
 *
 * @Author:gkr
 * @Date:2022-03-01 23:21
 */
@RestController
@RequestMapping(value = "/redis")
public class RedisController {

    @Autowired
    ShopService shopService;

    @GetMapping(value = "/{id}")
    public Shop findById(@PathVariable("id")long id){
        return shopService.selectById(id);
    }

}
