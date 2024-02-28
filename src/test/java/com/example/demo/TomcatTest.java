package com.example.demo;

import com.alibaba.fastjson.JSON;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.Context;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class TomcatTest {
    // 模拟SpringBoot启动容器
    public static void main(String[] args) throws LifecycleException {
        TomcatTest.run();
    }

    public static void run() throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(1010);
        tomcat.start();

        Context context = tomcat.addContext("/", "/Users/Lee/WorkSpace/zysDemo2/src/main/webapp");
        tomcat.addServlet("/", "dispathcher", new DispatcherServlet());
        //匹配所有访问
        context.addServletMappingDecoded("/*", "dispathcher");
        tomcat.getServer().await();
    }

}

class DispatcherServlet implements Servlet {

    @Override
    public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
        HttpServletRequest servletRequest = (HttpServletRequest) req;
        String requestURI = servletRequest.getRequestURI();
        System.out.println("当前请求URI："+requestURI);
        System.out.println("当前请求参数："+ JSON.toJSONString(servletRequest.getParameterMap()));
    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }

    // 省略一些方法
}
