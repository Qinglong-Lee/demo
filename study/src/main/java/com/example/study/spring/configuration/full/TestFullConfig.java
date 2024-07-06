package com.example.study.spring.configuration.full;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

//默认proxyBeanMethods = true
//表示代理模式，即全配置类，保证bean是单例
//false表示版配置类，无法保证此配置类中bean是单例
@Configuration(/*proxyBeanMethods = false*/)
public class TestFullConfig {
//    注意不能是静态方法，否则单例模式会失效
//    怀疑是代理模式无法代理静态方法
    @Bean
    public /*static*/ TestFullConfigBean testFullConfigBean() {
        return new TestFullConfigBean();
    }
    @Bean
    public TestFullConfigBean2 testFullConfigBean2() {
        return new TestFullConfigBean2(testFullConfigBean());
    }

//    内部类如果使用@Component（@Configuration也是@Component），@ComponentScan，@Import，@ImportResource（用于导入配置文件）这些注解的其中一个
//    也会被注册成一个单例bean到IOC
    @Component
    public class MemberClass {
        public String name = "CONFIGURATION MEMBER CALSS";
    }
}
