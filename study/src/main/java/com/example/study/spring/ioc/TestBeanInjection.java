package com.example.study.spring.ioc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestBeanInjection {
    /**
     * 属性注入无法对静态属性生效
     * 静态属性需用方法注入（setter或自定义方法）
     */
    @Autowired
//    Lazy注解用在类的属性上，表示延迟实例化此属性引用的bean，
//    即在实例化TestBeanInjection时会忽略TestBean，即使TestBean没有被实例化也不会报错，更不会去实例化TestBean
//    所以如果要真正的懒加载TestBean，不仅需要在TestBean的定义上加Lazy注解，还需要在其它Bean对TestBean的引用上加Lazy注解
//    @Lazy
    public /*static*/ TestBean testBean;

    /**
     * 不需要@Autowired
     */
//    public TestBeanInjection(TestBean testBean) {
//        this.testBean = testBean;
//    }

    /**
     * 需要@Autowired
     */
//    @Autowired
//    public void setTestBean(TestBean testBean) {
//        this.testBean = testBean;
//    }

//    @Autowired
//    public void initTestBean(TestBean testBean) {
//        this.testBean = testBean;
//    }
}
