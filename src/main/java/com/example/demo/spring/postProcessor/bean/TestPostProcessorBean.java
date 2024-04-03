package com.example.demo.spring.postProcessor.bean;

import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * BeanPostProcessor是Bean的容器级生命周期接口
 * 意味着将作用于所有bean，而不是只作用于当前bean
 * 用于在bean初始化前后做前置和后置处理
 */
@Data
@Configuration
public class TestPostProcessorBean implements BeanPostProcessor {
    private String name;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
//        System.out.println("postProcessBeforeInitialization----" + beanName);
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//        System.out.println("postProcessAfterInitialization----" + beanName);
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
