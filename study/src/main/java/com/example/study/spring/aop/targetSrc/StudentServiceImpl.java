package com.example.study.spring.aop.targetSrc;

import org.springframework.stereotype.Service;

@Service("studentServiceImpl")
public class StudentServiceImpl implements StudentService{

    @Override
    public void eat(String a) {
        System.out.println("=====StudentServiceImpl.eat");
    }
}
