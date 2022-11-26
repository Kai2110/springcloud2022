package cn.com.kai.controller;

import cn.com.kai.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;


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
    private RedisService redisService;

    @GetMapping(value = "/getAllKeys")
    public Set getAllKeys(){
        return redisService.getAllKeys();
    }

}
