package cn.com.kai.controller;

import cn.com.kai.common.CommonResult;
import cn.com.kai.service.OpenFeignTest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * ClassName:OpenFeignTestController
 * Package:cn.com.kai.controller
 * Description:
 *
 * @Author:gkr
 * @Date:2022-01-13 17:06
 */
@RestController
public class OpenFeignTestController {

    private int count;

    @Resource
    private OpenFeignTest openFeignTest;

    @RequestMapping(value = "testOpenFeign/{commodity}")
    private CommonResult testOpenFeign(@PathVariable(value = "commodity")String commodity){
        CommonResult result = openFeignTest.getRepertory(commodity);
        return result;
    }

    @GetMapping(value = "/getCount")
    public void getCount(){
        count++;
        System.out.println(count);
    }

}
