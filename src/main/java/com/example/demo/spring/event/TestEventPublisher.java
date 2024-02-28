package com.example.demo.spring.event;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
//以下注解不知道为什么没生效
//@RequiredArgsConstructor
public class TestEventPublisher {
//    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    //构造器注入ApplicationEventPublisher
    public TestEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publish(String message) {
        // [2]使用publishEvent方法发布事件，事件源为TestEvent
        applicationEventPublisher.publishEvent(new TestEvent(this, message));
    }
}