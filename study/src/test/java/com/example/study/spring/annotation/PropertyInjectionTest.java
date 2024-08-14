package com.example.study.spring.annotation;

import cn.hutool.extra.spring.SpringUtil;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class PropertyInjectionTest {

    @Test
    public void testAutowired() {
        PropertyInjection pi = (PropertyInjection)SpringUtil.getBean("propertyInjection");
        BeanInterface myBean = pi.bean1;
        System.out.println(Optional.ofNullable(myBean).isPresent() ? myBean.getClass() : "Not Found");
    }

    @Test
    public void testResource() {
        PropertyInjection pi = (PropertyInjection)SpringUtil.getBean("propertyInjection");
        BeanInterface myBean = pi.bean2;
        System.out.println(Optional.ofNullable(myBean).isPresent() ? myBean.getClass() : "Not Found");
    }
}