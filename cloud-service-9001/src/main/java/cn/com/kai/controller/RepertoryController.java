package cn.com.kai.controller;

import cn.com.kai.common.CommonResult;
import cn.com.kai.result.ResultCode;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * ClassName:DemoController
 * Package:cn.com.kai.controller
 * Description:
 *
 * @Author:gkr
 * @Date:2022-01-11 13:11
 */
@RestController
public class RepertoryController {

    // 获取库存
    @RequestMapping(value = "/getRepertory/{commodityName}")
    public CommonResult getRepertory(@PathVariable(value = "commodityName",required = true)String commodityName){
        try {
            for (int i = 0; i < 2; i++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        CommonResult<String> result = new CommonResult<String>(ResultCode.SUCCESS.getCode(),
                "",commodityName+"的库存：");
        return result;
    }

    //添加库存
    @RequestMapping(value="addRepertory/{commodityName}")
    public CommonResult addRepertory(@PathVariable(value = "commodityName",required = true)String commodityName){
        CommonResult<String> result = new CommonResult<>(ResultCode.SUCCESS.getCode(),
                "成功添加商品："+commodityName,null);
        return result;
    }


}
