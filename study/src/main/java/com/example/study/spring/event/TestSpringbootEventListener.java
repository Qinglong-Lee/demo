package com.example.study.spring.event;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 自定义Spring生命周期事件监听器
 * 注意：此出监听的是ApplicationReadyEvent，所以可以监听到
 * 但是如果是ApplicationStartingEvent事件就不行
 * 因为户自定义的监听器是在所有Bean初始化完成后但实例化之前注册到容器（AbstractApplicationListener.registerListeners()）
 * 此时大部分生命周期事件已经广播
 * 所以自定义生命周期事件监听器只能监听到这之后的事件
 */
@Component
public class TestSpringbootEventListener implements ApplicationListener<ApplicationReadyEvent> {
    @Override
    public void onApplicationEvent(ApplicationReadyEvent testEvent) {
        // [3]在这里可以执行监听到事件后的逻辑, 监听到事件源，触发动作！
        System.out.println("监听到ApplicationReadyEvent");
    }
}