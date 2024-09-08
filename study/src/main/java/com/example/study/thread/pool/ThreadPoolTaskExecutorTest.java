package com.example.study.thread.pool;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * ●org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
 * ●是spring对java.util.concurrent.ThreadPoolExecutor的封装
 * ●ThreadPoolTaskExecutor继承自ExecutorConfigrationSupport
 * ●ExecutorConfigrationSupport实现了InitializingBean接口
 * ○并重写了afterPropertiesSet方法去调用initialize方法
 * ●ExecutorConfigrationSupport的initialize方法会调用子类ThreadPoolTaskExecutor的initializeExecutor方法去真正构造一个ThreadPoolExecutor
 * ●在Spring扫描bean的时候就会执行afterPropertiesSet进而构造一个ThreadPoolExecutor
 * ●意义
 * ○ThreadPoolExecutor是一个不受Spring管理生命周期、参数装配的Java类
 * ○Spring通过对其封装，用户可以通过注解的方式方便地构造ThreadPoolExecutor
 * ○只需传入相应配置即可，Spring会接管其构造过程
 */
public class ThreadPoolTaskExecutorTest {

    @Bean("taskExecutor")
    public ThreadPoolTaskExecutor taskExecutor() {

        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(2);
        taskExecutor.setMaxPoolSize(2);
        // 开启「停机时等待已提交任务的执行完成」特性
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        // 设置最大等待时长
        taskExecutor.setAwaitTerminationSeconds(120);
        // 初始化任务执行器，此后执行器就进入工作状态了
        taskExecutor.initialize();

        return taskExecutor;

    }

    /**
     * 利用 Spring 生命周期中的 Bean 销毁回调，触发执行器 shutdown 方法的调用
     */
    @Component
    public class ExecutorShutdownHook implements DisposableBean {
        @Resource
        ThreadPoolTaskExecutor taskExecutor;

        // 进程结束前，会调用本方法

        @Override
        public void destroy() throws Exception {
            System.out.println("[ShutdownHook]: 开始停止任务执行器");

            taskExecutor.shutdown();

            System.out.println("[ShutdownHook]: 任务执行器已平滑停止");
        }

    }
}
