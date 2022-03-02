package cn.com.kai.controller;

import cn.com.kai.common.CommonResult;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName:SentinelTestController
 * Package:cn.com.kai.controller
 * Description:
 *
 * @Author:gkr
 * @Date:2022-02-08 15:55
 */
@RestController
@RequestMapping(value = "/sentinel")
public class SentinelTestController {

    @GetMapping(value = "testA")
    private CommonResult testA(){
        CommonResult<String> result = new CommonResult<>();
        result.setData("testA...");
        return result;
    }

    @GetMapping(value = "testB")
    private CommonResult testB(){
        CommonResult<String> result = new CommonResult<>();
        result.setData("testB...");
        return result;
    }
}
