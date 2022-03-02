package cn.com.kai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * ClassName:CloudService9200Application
 * Package:cn.com.kai
 * Description:
 *   step01：添加sentinel的依赖包
 *   step02：添加sentinel的配置信息
 *
 * @Author:gkr
 * @Date:2022-02-08 15:44
 */
@SpringBootApplication
@EnableDiscoveryClient
public class CloudService9200Application {
    public static void main(String[] args) {
        SpringApplication.run(CloudService9200Application.class,args);
    }
}
