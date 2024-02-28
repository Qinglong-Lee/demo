package com.example.demo.spring.circleReference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BeanA {
    @Autowired
    public BeanB beanB;

    public void a() {
        beanB.b();
    }
}
