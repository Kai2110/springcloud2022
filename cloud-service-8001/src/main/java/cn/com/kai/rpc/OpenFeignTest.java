package cn.com.kai.rpc;

import cn.com.kai.common.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ClassName:OpenFeignTest
 * Package:cn.com.kai.rpc
 * Description: 远程调用OpenFeign测试类
 *      1.添加openFeign依赖包
 *      2.启动类上添加@EnableFeignClients注解，激活OpenFeign
 *
 * @Author:gkr
 * @Date:2022-01-13 16:46
 */
@FeignClient(value = "cloud-service-9001",fallback = OpenFeignTestImpl.class)//指定需要调用的服务名
public interface OpenFeignTest {

    @RequestMapping(value = "/getRepertory/{commodityName}")
    public CommonResult getRepertory(@PathVariable(value = "commodityName",required = true)String commodityName);
}
