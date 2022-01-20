package cn.com.kai.controller;

import cn.com.kai.common.CommonResult;
import cn.com.kai.rpc.OpenFeignTest;
import org.omg.CORBA.TIMEOUT;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

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

    @Resource
    private OpenFeignTest openFeignTest;

    @RequestMapping(value = "testOpenFeign/{commodity}")
    private CommonResult testOpenFeign(@PathVariable(value = "commodity")String commodity){
        CommonResult result = openFeignTest.getRepertory(commodity);
        return result;
    }

}
