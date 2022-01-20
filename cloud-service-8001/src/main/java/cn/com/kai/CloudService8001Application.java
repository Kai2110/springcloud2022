package cn.com.kai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * ClassName:CloudService8001Application
 * Package:cn.com.kai
 * Description:
 *
 * @Author:gkr
 * @Date:2022-01-11 13:38
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class CloudService8001Application {
    public static void main(String[] args) {
        SpringApplication.run(CloudService8001Application.class,args);
    }
}
