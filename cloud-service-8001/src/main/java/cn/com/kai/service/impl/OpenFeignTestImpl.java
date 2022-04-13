package cn.com.kai.service.impl;

import cn.com.kai.common.CommonResult;
import cn.com.kai.service.OpenFeignTest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * ClassName:OpenFeignTestImpl
 * Package:cn.com.kai.rpc
 * Description: RPO远程调用OpenFeign的降级处理
 *      step01：编写一个类，实现远程调用接口
 *      step02：在远程调用接口中，通过@FeignClient的fallback类指定降级处理时调用的类
 *      step03: 修改YML文件：开启OpenFeign的hystrix功能
 *
 * @Author:gkr
 * @Date:2022-01-20 13:06
 */
@Component
@RefreshScope
public class OpenFeignTestImpl implements OpenFeignTest {

    @Value("${trouble.msg500}")
    private String msg500;

    @Override
    public CommonResult getRepertory(String commodityName) {
        CommonResult<String> result = new CommonResult<>();
        result.setCode(500);
        result.setMessage(msg500);
        return result;
    }
}
