package com.example.demo.spring.aop.targetSrc;

import org.springframework.aop.framework.autoproxy.target.AbstractBeanFactoryBasedTargetSourceCreator;
import org.springframework.aop.target.AbstractBeanFactoryBasedTargetSource;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class CustomTargetSourceCreator extends AbstractBeanFactoryBasedTargetSourceCreator {

    @Override
    protected AbstractBeanFactoryBasedTargetSource createBeanFactoryBasedTargetSource(Class<?> beanClass, String beanName) {
        if (getBeanFactory() instanceof ConfigurableListableBeanFactory) {
            if(beanClass.isAssignableFrom(StudentServiceImpl.class)) {
                return new CustomTargetSource();
            }
        }
        return null;
    }
}

