package com.example.study.spring.di.setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class SetterA {

    //setter注入的方式无法用final修饰符修饰属性
    //因为setter注入是当前bean实例化时候为属性赋值，视图改变属性值
    public /*final*/ SetterB setterB;

    //setter依赖有可能依赖为空
    //required默认是true
    @Autowired(required = false)
    public void setCircB(SetterB setterB) {
        this.setterB = setterB;
    }

}

