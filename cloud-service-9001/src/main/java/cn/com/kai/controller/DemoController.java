package cn.com.kai.controller;

import cn.com.kai.common.CommonResult;
import cn.com.kai.common.ResultCode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName:DemoController
 * Package:cn.com.kai.controller
 * Description:
 *
 * @Author:gkr
 * @Date:2022-01-11 13:11
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    @RequestMapping(value = "/index")
    public CommonResult index(){
        CommonResult<String> result = new CommonResult<>();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage("成功");
        return result;
    }



}
