package com.example.study.spring.runner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 自定义ApplicationRunner将在容器初始化完成后被调用
 * 用于自定义启动时的初始化操作
 */
@Component
public class TestApplicationRunner implements ApplicationRunner {
    // ApplicationArguments, 需要区分选项参数和非选项参数;
    // 以 args=new String[]{"--name=ARG1", "ARG2"}为例
    // 选项参数, 通过ApplicationArguments的 getOptionNames()方法获取所有选项名称即参数的KEY: name, 然后通过 getOptionValues()方法根据参数KEY, 获取实际值(它会返回一个列表字符串), 一般为： --user-name=ROCKY --age=30
    // 非选项参数, 通过ApplicationArguments的 getNonOptionArgs()方法获取一个参数值数组: ARG2
    // 原始值参数，通过ApplicationArguments的 getSourceArgs()方法获取参数的原始值：[--name=ARG3, ARG2]
    @Override
    public void run(ApplicationArguments args) throws Exception {
        /*Arrays.stream(args.getSourceArgs()).forEach(System.out::println);
        args.getNonOptionArgs().stream().forEach(System.out::println);
        args.getOptionNames().stream().forEach(System.out::println);*/
    }
}
