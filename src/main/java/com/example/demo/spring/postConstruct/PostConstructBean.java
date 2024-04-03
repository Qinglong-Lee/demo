package com.example.demo.spring.postConstruct;

import cn.hutool.extra.spring.SpringUtil;
import com.example.demo.spring.aware.ApplicationContextAwareTest;
import com.example.demo.spring.ioc.SpringUtils;
import com.example.demo.spring.ioc.TestBean;
import com.example.demo.spring.listener.ApplicationListenerTest;
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
