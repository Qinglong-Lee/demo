package com.example.demo.spring.aware;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

@Component
public class AwareBean implements BeanNameAware {
    public String beanName;
    @Override
    public void setBeanName(String name) {
        System.out.println("BeanNameAware");
        beanName = name;
    }
}
