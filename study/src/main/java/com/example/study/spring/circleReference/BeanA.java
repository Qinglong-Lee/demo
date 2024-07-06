package com.example.study.spring.circleReference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
@Lazy
@Component
public class BeanA {
    @Autowired
    public BeanB beanB;

    public void a() {
        beanB.b();
    }
}
