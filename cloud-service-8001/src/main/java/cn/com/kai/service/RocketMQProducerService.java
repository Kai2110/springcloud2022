package cn.com.kai.service;

import org.apache.rocketmq.common.protocol.body.ConsumeMessageDirectlyResult;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * ClassName:RocketMQProducer
 * Package:cn.com.kai.service
 * Description: RocketMQ生产者
 *  SpringBoot集成RocketMQ的步骤：
 *      rocketmq-spring-boot-starter还没有加入到maven中心库中，因此需要自己安装到本地仓库
 *      1.下载rocketmq-spring（https://github.com/apache/rocketmq-spring）
 *      2.将rocketmq-spring安装到本地仓库（mvn -isntall -Dmaven.skip.test=true）
 *
 *      3.引入RocketMQ的依赖包
 *      4.添加RocketMQ的配置信息
 *
 * @Author:gkr
 * @Date:2022-04-12 13:26
 */
@Service
public class RocketMQProducerService {

//    @Autowired
//    private RocketMQTemplate template;

    /**
     * 发送消息
     */
    public void sendMessage(){
//        template.convertAndSend("StringTopic","发送普通字符串");
    }


}
