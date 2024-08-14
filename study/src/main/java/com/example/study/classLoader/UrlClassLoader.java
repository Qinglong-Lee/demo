package com.example.study.classLoader;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * 测试 URLClassLoader
 * 分别从本地和 nginx 加载 class 文件
 *
 * 注意：
 * loadClass 方法参数是【全类名】，因为 class 文件必须用全类名定位，否则无法加载
 * 指定的 URL 如果是 file 类型，则会将【URL + 全类名】作为查找路径，因此 URL 通常为【要查找的类所在的根路径】
 * 即【D:\\WORK\\codes\\demo\\study\\src\\main\\java\\】+【com.example.study.rmi.jndiVulnerability.evil】+【EvilClass】+【.class】
 * 测试的时候要将 java 源文件删除，否则会因为 IDE 能够定位到 java 文件而忽略了 URL 指定的路径，导致无论给什么 URL 都能找到的假象，其实找到的是 java 文件而非 class 文件
 *
 */
public class UrlClassLoader {
    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        File xFile=new File("D:\\WORK\\codes\\demo\\study\\src\\main\\java\\");


        URL xUrl= null;
        try {
            xUrl = xFile.toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        URLClassLoader loader = new URLClassLoader(
            new URL[] {
//                    new URL("file:\\E:\\STUDY\\")
                    xUrl
//                        new URL("http://localhost/com/example/study/rmi/jndiVulnerability/evil/")
            });

//        System.out.println(loader.loadClass("com.example.study.rmi.jndiVulnerability.evil.EvilClass"));
//        loader.loadClass("com.example.study.rmi.jndiVulnerability.evil.EvilClass").getDeclaredConstructor().newInstance();
        System.out.println(loader.loadClass("com.example.study.classLoader.SimpleClass2"));
    }

}
