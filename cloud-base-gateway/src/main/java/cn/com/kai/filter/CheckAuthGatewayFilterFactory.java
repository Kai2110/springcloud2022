package cn.com.kai.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

/**
 * ClassName:CheckAuthGatewayFilterFactory
 * Package:cn.com.kai.filter
 * Description:自定义过滤器：模拟网关默认过滤器  (局部过滤器)
 * 1.类名必须以GatewayFilterFactory结尾
 * 2.继承AbstractGatewayFilterFactory类,重写apply()方法
 * 3.声明一个静态内部类用于接收配置信息
 * 4.在配置文件中进行配置，过滤名称为类名前缀，此处为CheckAuth
 *
 * @Author:gkr
 * @Date:2022-04-28 17:44
 */
@Component
public class CheckAuthGatewayFilterFactory extends AbstractGatewayFilterFactory<CheckAuthGatewayFilterFactory.Config> {

    //1.初始化静态内部类
    public CheckAuthGatewayFilterFactory() {
        super(CheckAuthGatewayFilterFactory.Config.class);
    }

    //2.静态内部类：定义属性来接收自定义过滤器指定的数据
    public static class Config {
        private String token;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }

    @Override  //3.通过该方法进行配置信息的绑定
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("token");
    }

    @Override //4.进行请求拦截
    public GatewayFilter apply(Config config) {
        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                //获取配置文件中的数据
                String token1 = config.getToken();
                //获取请求参数
                String token = exchange.getRequest().getQueryParams().getFirst("token");
                if (!token1.equals(token)) {
                    ServerHttpResponse response = exchange.getResponse();
                    response.setStatusCode(HttpStatus.NOT_FOUND);
                    return response.setComplete();//拦截请求，返回客户端404
                }
                return chain.filter(exchange);//继续向下执行
            }
        };
    }


}
