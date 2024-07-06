package com.example.study.spring.aware;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 利用ApplicationContextAware接口获取ApplicationContext经测试是没问题的
 * 不像网上有人说的会对@PostConstructs报空指针（不知道是不是Springboot的加载顺序和SpringMVC不一样）
 * 此接口会在invokeBeanFactoryPostProcessors的时候被回调，此时bean还没有被实例化呢
 */
@Component
public class ApplicationContextAwareTest implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public static <T> T getBean(String name) throws BeansException {
        return (T) applicationContext.getBean(name);
    }
}
