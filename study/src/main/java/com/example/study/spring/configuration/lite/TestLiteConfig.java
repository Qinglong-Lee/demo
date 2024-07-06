package com.example.study.spring.configuration.lite;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class TestLiteConfig {

    @Bean
    public TestLiteConfigBean testLiteConfigBean() {
        return new TestLiteConfigBean();
    }
    @Bean
    public TestLiteConfigBean2 testLiteConfigBean2(TestLiteConfigBean testLiteConfigBean) {
//        此种依赖定义将无法在单例模式下进行
//        return new TestLiteConfigBean2(testLiteConfigBean());
//        利用方法参数解决半配置类的非单例问题
        return new TestLiteConfigBean2(testLiteConfigBean);
    }

}
