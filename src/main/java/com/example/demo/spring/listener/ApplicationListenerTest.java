package com.example.demo.spring.listener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * 利用ApplicationListener获取ApplicationContext有问题
 * 对于@PostConstruct注解标识的方法，在bean属性装配完成，初始化之前就会被调用
 * 如果在这个方法中使用此类获取applicationContext中的bean，applicationContext将为null
 * 因为ApplicationListener只会在容器初始化完成后被回调（finishRefresh()）
 * 而@PostConstruct方法的调用时机是在bean初始化之前，此时容器肯定还没初始化完，onApplicationEvent肯定还没回调
 * 用ApplicationContextAware接口获取ApplicationContext经测试是没问题的
 * 此接口会在invokeBeanFactoryPostProcessors的时候被回调，此时bean还没有被实例化呢
 */
@Component
public class ApplicationListenerTest implements ApplicationListener<ApplicationEvent> {
    private static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ContextRefreshedEvent) {
            ContextRefreshedEvent e = (ContextRefreshedEvent) event;
            if (e.getApplicationContext().getParent() == null) {
                applicationContext = e.getApplicationContext();
            }
        }
    }

    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }
}


