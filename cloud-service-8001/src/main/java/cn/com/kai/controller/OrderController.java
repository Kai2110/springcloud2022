package cn.com.kai.controller;

import cn.com.kai.common.CommonResult;
import cn.com.kai.domain.Order;
import cn.com.kai.result.ResultCode;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * ClassName:Demo8001Controller
 * Package:cn.com.kai.controller
 * Description:测试用例
 *
 * @Author:gkr
 * @Date:2022-01-11 13:44
 */

@RestController
public class OrderController {

    @RequestMapping(value = "/list")
    private CommonResult list(){
        ArrayList<Order> list = new ArrayList<>();
        list.add(new Order(1,1,1,1,new BigDecimal(1),1));
        list.add(new Order(2,2,2,1,new BigDecimal(2),2));
        list.add(new Order(3,3,3,1,new BigDecimal(3),3));
        return  new CommonResult<List<Order>>(ResultCode.SUCCESS.getCode(), " ",list);
    }

    @RequestMapping(value = "getOrderSize/{orderId}")
    private CommonResult getOrderSize(@PathVariable(value = "orderId",required = true) String orderId){
        CommonResult<String> result = new CommonResult<>(ResultCode.SUCCESS.getCode(), "", orderId + "的订单量为：");
        return  result;
    }

}
