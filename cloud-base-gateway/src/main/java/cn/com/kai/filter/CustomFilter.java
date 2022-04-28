package cn.com.kai.filter;

/**
 * ClassName:CustomFilter
 * Package:cn.com.kai.predicate.filter
 * Description: 自定义过滤器
 * 过滤器包括GatewayFilter、GlobalFilter和过滤器链GatewayFilterChain，都依赖ServerWebExchange。
 * 当前过滤器可以决定是否执行下一个过滤器的逻辑，由GatewayFilterChain#filter()是否被调用来决定，
 * 而ServerWebExchange就相当于当前请求和响应的上下文，存放着重要的请求-响应属性、请求实例和响应实例等
 *
 *  一、自定义网关过滤器的方式
 *      1.继承AbstractGatewayFilterFactory(推荐)：该方式模拟默认的网关过滤器，然后通过配置文件配置即可
 *
 *      2.实现GatewayFilter接口（不推荐）：需要通过getOrder()方法设置过滤器执行顺序，数值月底优先级越高；同时还需要java代码编写路由信息添加过滤器
 *
 *  二、过滤器的生命周期
 *      1.pre：在请求被执行前调用
 *
 *      2.post：在请求被执行后调用
 *
 * @Author:gkr
 * @Date:2022-04-28 17:08
 */
public class CustomFilter {
}
