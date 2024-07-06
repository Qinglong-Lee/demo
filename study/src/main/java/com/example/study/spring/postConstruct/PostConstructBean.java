package com.example.study.spring.postConstruct;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class PostConstructBean {
    @PostConstruct
    public void postConstruct() {
        System.out.println("进入@PostConstruct");
//        ApplicationContextAwareTest.getBean("testBean");
//        ApplicationListenerTest.getBean(TestBean.class);
    }
}
