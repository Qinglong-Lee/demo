package com.example.demo.spring.di.manual;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class AutoWiredBean {
    @Lazy
    @Autowired
    public ManualLoadBean manualLoadBean;
}

