package cn.com.kai.controller;

import cn.com.kai.common.CommonResult;
import cn.com.kai.common.ResultCode;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName:Demo8001Controller
 * Package:cn.com.kai.controller
 * Description:测试用例
 *
 * @Author:gkr
 * @Date:2022-01-11 13:44
 */

@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @RequestMapping(value = "getOrderSize/{name}")
    private CommonResult getOrderSizeByName(@PathVariable(value = "name",required = true) String name){
        CommonResult<String> result = new CommonResult<>();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setData(name+"的订单量为：");
        return result;
    }
}
