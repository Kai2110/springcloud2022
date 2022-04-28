package cn.com.kai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.time.ZonedDateTime;


@EnableDiscoveryClient
@SpringBootApplication
public class  GatewayApplication {
    //TODO  1.请求日志
    //TODO  2.认证授权、鉴权
    //TODO  3.请求限流
    //TODO  4.熔断处理
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
        System.out.println(ZonedDateTime.now());
    }
}
