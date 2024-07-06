package com.example.study.spring.aop.targetSrc;

import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.AopProxy;
import org.springframework.aop.support.AopUtils;
import java.lang.reflect.Field;

/**
 * @ClassName SpringAopTargetUtil
 * @Description 在 spring aop 对容器中的对象代理以后 通过spring的接口获取的对象都是代理后的对象
 *                 该工具类能获取到被代理的对象
 * @Author Jiajiajia
 * @Date 2020/12/20 14:42
 **/
public class SpringAopTargetUtil {
    /**
     * 获取被代理类的Object
     */
    public static Object getTarget(Object proxy) throws Exception {
        if(!AopUtils.isAopProxy(proxy)) {
            /**
             * 不是代理对象
             */
            return proxy;
        }
        if(AopUtils.isJdkDynamicProxy(proxy)) {
            /**
             * jdk代理
             */
            return getJdkDynamicProxyTargetObject(proxy);
        } else {
            /**
             * cglib 代理
             */
            return getCglibProxyTargetObject(proxy);
        }
    }
    /**
     * @MethodName: getCglibProxyTargetObject
     * @Description: CGLIB方式被代理类的获取
     * @Author: Jiajiajia
     * @Params:  * @param proxy
     * @Return {@link Object}
     * @date 2020/12/20
     */
    private static Object getCglibProxyTargetObject(Object proxy) throws Exception {
        Field h = proxy.getClass().getDeclaredField("CGLIB$CALLBACK_0");
        h.setAccessible(true);
        Object dynamicAdvisedInterceptor = h.get(proxy);
        Field advised = dynamicAdvisedInterceptor.getClass().getDeclaredField("advised");
        advised.setAccessible(true);
        Object target = ((AdvisedSupport)advised.get(dynamicAdvisedInterceptor)).getTargetSource().getTarget();
        return target;
    }
    /**
     * @MethodName: getJdkDynamicProxyTargetObject
     * @Description: JDK动态代理方式被代理类的获取
     * @Author: Jiajiajia
     * @Params:  * @param proxy
     * @Return {@link Object}
     * @date 2020/12/20
     */
    private static Object getJdkDynamicProxyTargetObject(Object proxy) throws Exception {
        Field h = proxy.getClass().getSuperclass().getDeclaredField("h");
        h.setAccessible(true);
        AopProxy aopProxy = (AopProxy) h.get(proxy);
        Field advised = aopProxy.getClass().getDeclaredField("advised");
        advised.setAccessible(true);
        Object target = ((AdvisedSupport)advised.get(aopProxy)).getTargetSource().getTarget();
        return target;
    }
}
