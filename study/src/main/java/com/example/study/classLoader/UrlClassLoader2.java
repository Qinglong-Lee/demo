package com.example.study.classLoader;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * 测试 URLClassLoader
 * 分别从本地和 nginx 加载 class 文件
 *
 * TODO: 奇怪问题，第一次加载 class 必须在跟目录下，即不能有 package，否则【NoClassDefFoundError: Wrong name】
 * 而第一次加载之后，再加载有 package 的 class 就能成功
 *
 */
public class UrlClassLoader2 {
    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
//        第一次非要在根目录下
//        File xFile=new File("D:\\WORK\\codes\\demo\\study\\src\\main\\java\\");
//        之后就可以随便指定目录
        File xFile=new File("D:\\WORK\\codes\\demo\\study\\src\\main\\java\\com\\example\\study\\classLocader\\");

        URL xUrl= null;
        try {
            xUrl = xFile.toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        URLClassLoader loader = new URLClassLoader(
            new URL[] {
                    xUrl
//                        new URL("http://localhost/rmi/")
            });

        System.out.println(loader.loadClass("tmp.SimpleClass"));
    }
}
