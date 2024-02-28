package com.example.demo.spring.circleReference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BeanB {
    @Autowired
    public BeanA beanA;

    public void b() {
        beanA.a();
    }
}
