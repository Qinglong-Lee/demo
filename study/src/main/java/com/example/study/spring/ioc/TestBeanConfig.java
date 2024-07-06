package com.example.study.spring.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestBeanConfig {
    /**
     * @Bean一般用于注册非当前类的其他类型的bean
     * initMethod用于指定bean的初始化方法
     */
    //Lazy注解作用在bean的定义上，作用是延迟bean的实例化到第一次使用的时候，第一此使用包括其他bean实例化时对此bean的引用
    //所以如果要真正的懒加载TestBean，不仅需要在TestBean的定义上加Lazy注解，还需要在其它Bean对TestBean的引用上加Lazy注解
    //@Lazy
    @Bean(initMethod = "initMethod")
    public static TestBean testBean() {
        return new TestBean();
    }

}
