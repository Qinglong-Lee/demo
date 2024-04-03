package com.example.demo.spring.ioc;

import lombok.Data;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.Lifecycle;
import org.springframework.context.SmartLifecycle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import javax.annotation.PostConstruct;


/**
 * 定义一个bean
 * 继承自SmartLifecycle，意思这个bean是监听了IOC容器的生命周期
 * 当IOC容器初始化完成之后，会通过这个接口的start方法通知实现了这个接口的bean
 * 当调用context.stop()停止容器时会回调此方法通知bean，前提是isRunning方法返回true
 */
@Data
public class TestBean implements SmartLifecycle, InitializingBean, BeanNameAware {
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
     * 当依赖注入完成后用于执行初始化的方法
     * Constructor(构造方法) -> @Autowired(依赖注入) -> @PostConstruct(注释的初始化方法) -> @Bean(initMethod)
     */
    @PostConstruct
    public void postConstruct() {
        System.out.println("TestBean postConstruct");
    }

    /**
     * 初始化方法
     * 在@Bean(initMethod = "initMethod")中指定
     * 是在bean实例化之后生效的
     * 所以在构造方法中name=DEFAULT，而在启动之后获取的name=INIT-METHOD
     */
    private void initMethod() throws InterruptedException {
//        用睡眠10s证明bean的加载在容器中是一个同步进程，不会再获取bean的时候bean还没初始化完
//        System.out.println("TestBean initMethod SLEEP 10S");
//        Thread.sleep(10000);
        name = "INIT-METHOD";
        System.out.println("TestBean initMethod");
    }

    /**
     * 当调用context.stop()停止容器时会回调此方法通知bean
     * 前提条件是isRunning方法返回true
     */
    @Override
    public void stop() {
        System.out.println("LifeCycle stopped");
    }

    @Override
    public boolean isRunning() {
        return true;
    }

    @Override
    public int getPhase() {
        return DEFAULT_PHASE;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("TestBean afterPropertiesSet");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("TestBean BeanNameAware");
    }
}
