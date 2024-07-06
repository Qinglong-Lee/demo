package com.example.study.spring.aop.targetSrc;

import org.springframework.aop.target.AbstractBeanFactoryBasedTargetSource;

public class CustomTargetSource extends AbstractBeanFactoryBasedTargetSource {
    @Override
    public Object getTarget() throws Exception {
        return getBeanFactory().getBean(getTargetBeanName());
    }
}

