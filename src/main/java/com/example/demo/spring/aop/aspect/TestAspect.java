package com.example.demo.spring.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TestAspect {

    @Pointcut("execution(* com.example.demo.spring.aop.aspect.AspectBean.*(..))")
    public void webLoggingPointcut() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }


    @Around("webLoggingPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("进入切面方法");
        Object res = joinPoint.proceed();
        System.out.println("退出切面方法");
        return res;
    }
}
