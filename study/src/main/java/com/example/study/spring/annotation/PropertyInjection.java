package com.example.study.spring.annotation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class PropertyInjection {

    /**
     * @Autowired 测试
     * Bean 的注入首先就需要在容器中找到对应的 bean
     * 而查找 bean 无非就是根据两个层面：类型和 ID
     * 对于类型：如果 bean 有派生关系，那么就要指定具体子类型，否则由于多态无法找到唯一的 bean
     * 对于 ID，分为两种：bean 名称和 @Qualifier 定义的 ID
     * bean 名称的定义标准：如果 bean 类名前两个字母不是连续大写，则将首字母转换小写作为名称，否则用类名作为名称
     * 当 bean 类型定位到唯一 bean 时，会忽略 bean 名称
     * 但是当有 @Qualifier 时，一定会用 bean ID 去匹配 bean 名称
     * 即类型是必要项，ID 是只要有就必查项，名称是可选项，只有因为多态导致类型无法唯一匹配，才会使用名称匹配
     *
     * 允许通过 required 属性指明 bean 是否必须存在，默认为 true
     */
    @Autowired(required = false)
//    @Qualifier("bean2")
    @Qualifier("BEan4")
    public BeanInterface/*Bean3*/ bean1;

    /**
     * @Resouce 测试
     * 与 @Autowired 类似，本质上都是从类型和 ID 两个层面查找 bean
     * 但是 @Resource 先从 bean 名称开始匹配，然后再试类型
     * 和 @Autowired 一样，如果有 @Qualifier，就一定会用 bean ID 去匹配 bean 名称
     *
     * 比 @Autowired 多了一些配置属性，更加灵活：
     * name 属性可以指定具体名称，而 @Autowired 只能通过 bean 定义的变量名来匹配名称
     * type 属性可以指定具体类型，而 @Autowired 只能通过 Bean 定义的变量名类型指定
     * lookup 和 mappedName 用于指定一个 JNDI 远程对象，lookup 是 JNDI 的标准实现，是通用的，mappedName 只适用于少数特殊的第三方服务，不通用
     *
     * @Resource 没有 required 属性，即注入的 bean 必须存在
     */
    @Resource(type = Bean2.class, name = "bean2")
    public BeanInterface/*Bean3*/ bean2;

}
