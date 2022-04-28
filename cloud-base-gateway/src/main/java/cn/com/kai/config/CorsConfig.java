package cn.com.kai.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

/**
 * ClassName:CorsConfig
 * Package:cn.com.kai.config
 * Description: 跨域配置类
 *
 * 跨域除了通过配置类解决，还可以通过在配置文件中添加配置进行解决
 *
 * @Author:gkr
 * @Date:2022-04-28 21:52
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsWebFilter corsFilter(){
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedMethod("*");
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        //要这个包：org.springframework.web.cors.reactive
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",config);
        return new CorsWebFilter(source);
    }

}
