package com.example.study.spring.circleReference;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor;

//@Component
public class CustomSmartInstantiationAwareBeanPostProcessor implements SmartInstantiationAwareBeanPostProcessor {
    /**
     * AbstractAutowireCapableBeanFactory#getEarlyBeanReference会调用此方法
     * 此方法是三级缓存中bean工厂用来对bean对象做扩展的方法
     * 也就是说用户对bean的代理逻辑可以通过实现SmartInstantiationAwareBeanPostProcessor接口覆写getEarlyBeanReference得以实现
     * 注意：
     * 不是bean实例化前后的后处理器，那个接口是InstantiationAwareBeanPostProcessor，不是在这里处理的
     * 还要注意只有存在循环依赖才会执行此方法，因为只有存在循环依赖才会用到三级缓存中的bean工厂
     * 所以上面说的用户代理逻辑光实现着个还不够，还要考虑没有循环依赖的情况，此时会在初始化之后创建代理，参考AOP
     * 此接口是一个专用接口，主要用于框架内的内部使用，用于解决循环依赖，所以一般不会来实现此接口
     *
     * Spring的AOP在有循环依赖的情况下就是通过覆写了这个方法实现
     * org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator#getEarlyBeanReference(java.lang.Object, java.lang.String)
     * 初始化阶段AOP实现：
     * org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator#postProcessAfterInitialization(java.lang.Object, java.lang.String)
     * 继承自BeanPostProcessor
     *
     */
    @Override
    public Object getEarlyBeanReference(Object bean, String beanName) throws BeansException {
        System.out.println("CustomSmartInstantiationAwareBeanPostProcessor被调用");
        return bean;
    }
}
