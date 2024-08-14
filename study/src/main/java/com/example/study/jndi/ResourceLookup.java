package com.example.study.jndi;

import com.example.study.rmi.Hello.Hello;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 测试 @Resource 的 lookup 和 mappedName 属性
 * lookup 和 mappedName 都是用于指定一个 JNDI 地址
 * 意思是 @Resource 标记的属性的值是一个远程对象，即不是真实对象，而是一个代理
 * 原理是充当一个 JNDI 客户端，使用 JNDI 的 lookup 方法获取一个远程对象的代理，即 stub【存根】
 * 通过 stub 就可以调用远程对象的方法
 *
 * mappedName 是为少数特殊的第三方服务提供的方法，不通用
 * lookup 是标准 JNDI 实现，如 RMI，DNS，LDAP 等，是通用的 JNDI 接口
 */
@Component
public class ResourceLookup {
    @Resource(/*lookup = "rmi://localhost:1099/Hello"*/ mappedName = "rmi://localhost:1099/Hello")
    public Hello hello;
}
