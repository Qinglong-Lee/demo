package com.example.demo.spring.ioc;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.Lifecycle;
import org.springframework.context.SmartLifecycle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;


/**
 * 定义一个bean
 * 继承自SmartLifecycle，意思这个bean是监听了IOC容器的生命周期
 * 当IOC容器初始化完成之后，会通过这个接口的start方法通知实现了这个接口的bean
 */
@Data
public class TestBean implements SmartLifecycle {
    /**
     * 属性默认赋值在实例化的时候就生效，所以在构造方法中就能获得DEFAULT
     * @Value注解是在bean实例化之后初始化之前的依赖注入阶段（populateBean）生效的
     * 所以如果没有在初始化阶段重新赋值，则bean实例化之后得到的name=PROPERTY-INJECTION
     * 否则就会被覆盖为初始化赋值
     */
    @Value("${test.bean.value.injection}")
    public String name = "DEFALUT";
    public TestBean() {
        System.out.println("TestBean被实例化,name = " + name);
    }

    @Override
    public void start() {
        System.out.println("LifeCycle Started");
    }

    /**
     * 初始化方法
     * 在@Bean(initMethod = "initMethod")中指定
     * 是在bean实例化之后生效的
     * 所以在构造方法中name=DEFAULT，而在启动之后获取的name=INIT-METHOD
     */
    private void initMethod() {
        name = "INIT-METHOD";
    }

    @Override
    public void stop() {
        System.out.println("LifeCycle stopped");
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public int getPhase() {
        return DEFAULT_PHASE;
    }
}
