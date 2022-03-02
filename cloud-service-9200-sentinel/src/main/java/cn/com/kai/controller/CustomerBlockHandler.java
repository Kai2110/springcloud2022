package cn.com.kai.controller;

import cn.com.kai.common.CommonResult;
import com.alibaba.csp.sentinel.slots.block.BlockException;

/**
 * ClassName:CustomerBlockHandler
 * Package:cn.com.kai.controller
 * Description:
 *
 * @Author:gkr
 * @Date:2022-02-09 0:00
 */
public class CustomerBlockHandler {

    public  static CommonResult handlerBlockException(BlockException e){
        return new CommonResult(4444,"客户自定义，global handlerBlockException");
    }

    public  static CommonResult handlerBlockException2(BlockException e){
        return new CommonResult(4444,"客户自定义，global handlerBlockException2");
    }


}


