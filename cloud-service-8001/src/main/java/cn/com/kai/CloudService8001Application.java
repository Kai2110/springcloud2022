package cn.com.kai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

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
public class CloudService8001Application {
    public static void main(String[] args) {
        SpringApplication.run(CloudService8001Application.class,args);
    }
}
