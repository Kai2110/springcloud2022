package cn.com.kai.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * ClassName:LogFilter
 * Package:cn.com.kai.filter
 * Description: 自定义日志过滤器（全局过滤器）
 * 一、Gateway默认的全局过滤器
 *  1.LoadBalanceClientFilter  负载均衡客户端根据路由的URL解析转换成请求URL
 *  2.NettyRoutingFilter / NettyWriteResponseFilter  通过HttpClient客户端转发请求真实的URL并将响应写入到当前的请求响应中
 *
 * 二、自定义全局过滤器：针对所有请求，不需要进行任何配置
 * 1.实现GlobalFilter接口，用于定义编写过滤任务
 * 2.实现Ordered接口，用于定义过滤器的优先级，值越小优先级越高，优先被执行。
 *
 * @Author:gkr
 * @Date:2022-04-28 21:17
 */
@Component
public class LogGlobalFilter implements GlobalFilter,Ordered{

    Logger logger = LoggerFactory.getLogger(LogGlobalFilter.class);


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        logger.info(exchange.getRequest().getPath().value());
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
