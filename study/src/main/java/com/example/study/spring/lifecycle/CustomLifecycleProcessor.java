package com.example.study.spring.lifecycle;

import org.springframework.context.LifecycleProcessor;

/**
 * 自定义一个IOC容器生命周期处理器
 * 此处理器的onRefresh会在IOC容器初始化完成，bean被完全实例化到容器中后背调用
 * 如果没有自定义，默认使用的是DefaultLifeCycleProcessor
 * 默认处理器会调用所有实现了SmartLifeCycle的bean的start方法
 * 相当于通知这些bean容器初始化完成
 */
//@Component("lifecycleProcessor")
public class CustomLifecycleProcessor implements LifecycleProcessor {
    @Override
    public void onRefresh() {
        System.out.println("LifecycleProcessor().onRefresh()");
    }

    @Override
    public void onClose() {

    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
