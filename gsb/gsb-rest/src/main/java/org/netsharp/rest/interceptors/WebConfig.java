package org.netsharp.rest.interceptors;
import org.netsharp.rest.interceptors.LoginCheckInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * ClassName: WebConfig
 * @Description: TODO web相关配置
 * @author hbpeng <hbpeng@gongsibao.com>
 * @date 2018/4/17 19:07
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Bean
    LoginCheckInterceptor newSessionInterceptor() {
        return new LoginCheckInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //登陆拦截器
        registry.addInterceptor(newSessionInterceptor())
                .addPathPatterns("/**");
    }
}