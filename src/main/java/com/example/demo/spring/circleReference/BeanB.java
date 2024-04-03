package com.example.demo.spring.circleReference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
@Lazy
@Component
public class BeanB {

    @Autowired
    public BeanA beanA;

    public void b() {
        beanA.a();
    }
}
