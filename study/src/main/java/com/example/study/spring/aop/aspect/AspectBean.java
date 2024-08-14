package com.example.study.spring.aop.aspect;

import org.springframework.stereotype.Component;

@Component
public class AspectBean {
    public void testAspect1() {
        System.out.println("调用testAspect1");
        //注意：虽然使用AOP增强了AspectBean所有方法
        //但是此处调用testAspect2()并不会在走一遍增强流程
        //因为Spring AOP使用的是MethodProxy#invoke来调用被代理对象的原始方法的
        //此方法不会再次代理原始对象
        //而Spring配置类的代理是MethodProxy#invokeSuper
        //此方法会递归代理原始对象，因此即使在原始对象方法内部调用，也会被再次增强
        //从而实现递归配置
        testAspect2();
    }

    public void testAspect2() {
        System.out.println("调用testAspect2");
    }
}
