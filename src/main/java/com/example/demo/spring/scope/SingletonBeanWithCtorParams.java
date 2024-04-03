package com.example.demo.spring.scope;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SingletonBeanWithCtorParams {
    public String param1;
    public String param2;

    @Autowired
    public SingletonBeanWithCtorParams(String param1, String param2) {
        this.param1 = param1;
        this.param2 = param2;
    }

    public SingletonBeanWithCtorParams(String param1) {
        this.param1 = param1;
    }
}
