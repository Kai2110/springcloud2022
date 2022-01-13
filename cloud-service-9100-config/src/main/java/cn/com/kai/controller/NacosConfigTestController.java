package cn.com.kai.controller;

import cn.com.kai.common.CommonResult;
import cn.com.kai.result.ResultCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName:NacosConfigTestController
 * Package:cn.com.kai.controller
 * Description:
 *
 * @Author:gkr
 * @Date:2022-01-12 15:57
 */
@RestController
@RefreshScope  //支持Nacos动态刷新功能
public class NacosConfigTestController {
    @Value("${config.info}")        //获取配置文件中的数据
    private String configInfo;

    @RequestMapping("/getConfigInfo")
    public CommonResult getConfigInfo(){
        CommonResult<String> result = new CommonResult<>();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setData("Config info:"+configInfo);
        return result;
    }
}
