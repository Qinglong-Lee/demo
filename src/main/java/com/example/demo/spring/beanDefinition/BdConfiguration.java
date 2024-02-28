package com.example.demo.spring.beanDefinition;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Lazy;

/**
 * xml定义的bean和java配置类定义的bean的BeanDefinition是有区别的
 * xml定义bean的时候再文件中指定bean的所有信息，包括编译后的class,属性名和属性值，构造器等
 * 当Spring加载这些bean的时候就会将这些信息都加载到BeanDefinition中，然后利用这些信息来实例化bean
 * 所以可以理解为xml定义bean的初始化包括属性赋值和属性填充（populate 依赖其他类）
 *
 * java配置类定义的bean是直接用java类来定义bean的
 * 意味着Spring实例化bean的时候直接就从编译后的class文件中获取到了属性或构造器的信息
 * 然后直接实例化这个class即可，无需从BeanDefinition中获取
 * 所以可以理解为java配置类定义的bean的初始化没有属性赋值，只有属性填充，因为在实例化时就自动赋值了
 */
@Configuration
@ImportResource("classpath:applicationContext.xml")
public class BdConfiguration {
    @Bean
    @Lazy //懒加载这个bean是为了方便debug
    public ChildBean childBean() {
        return new ChildBean();
    }
}
