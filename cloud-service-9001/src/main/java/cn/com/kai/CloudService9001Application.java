package cn.com.kai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * ClassName:CloudService9001Application
 * Package:cn.com.kai
 * Description: 启动类
 *
 * @Author:gkr
 * @Date:2022-01-11 13:07
 */
@SpringBootApplication
@EnableDiscoveryClient
public class CloudService9001Application {
    public static void main(String[] args) {
        SpringApplication.run(CloudService9001Application.class, args);
    }
}
