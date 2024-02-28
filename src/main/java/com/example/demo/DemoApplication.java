package com.example.demo;

import com.example.demo.spring.beanDefinition.ChildBean;
import com.example.demo.spring.cofiguration.full.TestFullConfig;
import com.example.demo.spring.cofiguration.full.TestFullConfigBean2;
import com.example.demo.spring.cofiguration.lite.TestLiteConfigBean2;
import com.example.demo.spring.ioc.SpringUtils;
import com.example.demo.spring.ioc.TestBean;
import com.example.demo.spring.postProcessor.TestPostProcessorBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.metrics.jfr.FlightRecorderApplicationStartup;

import java.util.Arrays;

@SpringBootApplication
//如果要引用外部依赖并且想要自定扫描依赖中的配置，需要手动添加依赖的包路径，否则只会扫描当前包
//因此就体现了spring的自动配置SPI的方便之处，无需手动指定依赖包路径，spring直接通过依赖的resouces/META-INF/spring.factories来扫描需要自动配置的配置类
//@ComponentScan(basePackages = "com.example.springbootstarter")
public class DemoApplication {

	public static void main(String[] args) {

		SpringApplication springApplication = new SpringApplication(DemoApplication.class);
		//自定义应用上下文，即 bean 容器
//		springApplication.setApplicationContextFactory(new CustomizeApplicationContext.Factory());

//		配置一个java flight recorder（JRF）的跟踪器，默认跟踪器DefaultApplicationStartup不会记录指标到JRF
//		运行时指定-XX:StartFlightRecording:filename=recording.jfr,duration=10s将在项目根目录生成一个JRF文件
//		用于启动过程指标分析
//		其中BufferingApplicationStartup还会将运行指标缓存在内存，结合Spring actuator，可以通过监控接口访问指标信息
		springApplication.setApplicationStartup(new /*FlightRecorderApplicationStartup()*/BufferingApplicationStartup(4096));
//
		ConfigurableApplicationContext context =
				springApplication.run(args/*new String[]{"--name=ARG1", "ARG2"}*/);

//		如果一个bean的定义有继承关系，则会将子类和父类的BeanDefinition合并为MergedBeanDefinition
//		java类定义的bean，propertyValues中没有值？？？？那属性放在哪的？？？
//		BeanDefinition childBean = context.getBeanFactory().getBeanDefinition("childBean");
//		childBean.getPropertyValues().stream().forEach(System.out::println);
//		System.out.println(((ChildBean)context.getBeanFactory().getBean("childBean")).name);

//		对于xml定义的bean，会将bean的属性存放到 propertyValues
//		BeanDefinition sam = context.getBeanFactory().getBeanDefinition("sam");
//		sam.getPropertyValues().stream().forEach(System.out::println);
//		BeanDefinition mergedSam = context.getBeanFactory().getMergedBeanDefinition("sam");
//		mergedSam.getPropertyValues().stream().forEach(System.out::println);
		//		System.out.println("===================================================");
//		BeanDefinition merged = context.getBeanFactory().getMergedBeanDefinition("childBean");
//		System.out.println(((ChildBean)context.getBeanFactory().getBean("childBean")).parent);

//		System.out.println(context.getBeanFactory().getBean("parentBean"));
//		手动为beanFactory注册一个bean实例
//		((DefaultListableBeanFactory)context.getBeanFactory())
//				.registerSingleton("InstantiatedBean",
//						new InstantiatedBean());
//		SpringUtils.getBean("InstantiatedBean");
//		可以手动调用容器刷新方法，但是由于springboot继承自GenericWebapplicationContext,没有刷新功能，所以并不会重启容器
//		context.refresh();
//		System.out.println(
//				((TestBean)SpringUtils.getBean("testBean"))
//						.name);
//		TestBeanInjection testBeanInjection = SpringUtils.getBean("testBeanInjection");
//		System.out.println(testBeanInjection.testBean.name);
//		获取springboot-starter中的自动配置的bean
//		System.out.println(context.getBean("autoConfigBean"));
//		获取配置类的内部类的bean
//		需要使用配置类全类名
//		内部类需要使用@Component，@ComponentScan，@Import，@ImportResource这些注解的其中一个
//		System.out.println(((TestFullConfig.MemberClass)context.getBean("com.example.demo.spring.ioc.TestFullConfig$MemberClass"))
//				.name);
//		全配置类测试
//		System.out.println(context.getBean("testFullConfig"));
//		System.out.println(context.getBean("testFullConfigBean"));
//		System.out.println(
//				((TestFullConfigBean2)context.getBean("testFullConfigBean2"))
//						.testFullConfigBean
//		);
//		//配置类已经被代理，那么只要是调用此配置类的方法得到的bean都是单例
//		System.out.println(
//				((TestFullConfig)context.getBean("testFullConfig"))
//						.testFullConfigBean()
//		);
//		自己new的对象还是新的对象，全配置类的单例bean仅限于在配置类中被加载到IOC的bean
//		System.out.println(new TestFullConfigBean());
//		半配置类测试
//		System.out.println(context.getBean("testLiteConfig"));
//		System.out.println(context.getBean("testLiteConfigBean"));
//		System.out.println(
//				((TestLiteConfigBean2)context.getBean("testLiteConfigBean2"))
//						.testLiteConfigBean
//		);
//		被BeanFacPostProcessor处理的bean属性被更改
//		System.out.println(
//				((TestPostProcessorBean)context.getBean("testPostProcessorBean"))
//						.getName()
//		);
//		在ApplicationContext初始化完成后自定义的beanDefinition被加载前会先实例化一些特殊的bean到容器
//		应用参数ApplicationArguments就是其中一个，还有一个启动横幅springBootBanner
//		这样就可以在应用的任何地方获取到应用参数
//		System.out.println(
//				((ApplicationArguments)context.getBean("springApplicationArguments"))
//						.getSourceArgs()
//		);
	}

}
