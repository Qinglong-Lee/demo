package com.example.demo.spring.di.constructor;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Data
@Component
public class CtorA {

    //构造器注入可以用final修饰属性
    //因为是在实例化的时候注入的
    //这样可以保证注入之后永远不会被更改
    public final CtorB circB;

    @Autowired
    public CtorA(CtorB circB) {
        this.circB = circB;
    }
}

