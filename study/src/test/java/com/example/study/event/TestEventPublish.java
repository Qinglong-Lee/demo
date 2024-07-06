package com.example.study.event;

import com.example.study.spring.event.TestEventPublisher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestEventPublish {


    @Autowired
    private TestEventPublisher testEventPublisher;

    @Test
    void contextLoads() {
        // [1] 发布事件
        testEventPublisher.publish("Hello, Spring!");
    }
}
