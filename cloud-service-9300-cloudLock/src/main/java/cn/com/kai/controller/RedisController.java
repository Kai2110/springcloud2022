package cn.com.kai.controller;

import cn.com.kai.common.CommonResult;
import cn.com.kai.service.RedisService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


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

    @Resource
    private RedisService redisService;

    @GetMapping(value="/set/{name}")
    public CommonResult setName(@PathVariable(value="name")String name){
        CommonResult<String> result = new CommonResult<>();

        result.setData(redisService.setName(name).toString());
        return result;
    }
}
