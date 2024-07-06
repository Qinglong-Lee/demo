package com.example.study.generic;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

//此处 T 可以随便写为任意标识，常见的如 T、E、K、V 等形式的参数常用于表示泛型
//在实例化泛型类时，必须指定 T 的具体类型
//可以使用多个泛型标识以创建多泛型类 <T, K,...>
public class CustomeGeneric<T>{
    //key这个成员变量的类型为T，T 的类型由外部指定
    private T key;

    //泛型构造方法形参 key 的类型也为 T，T 的类型由外部指定
    public CustomeGeneric(T key) {
        this.key = key;
    }

    //泛型方法 getKey 的返回值类型为T，T 的类型由外部指定
    public T getKey(){
        return key;
    }

    public static<T> CustomeGeneric<T> produce(Supplier<? extends T> s) {
        return new CustomeGeneric<T>(s.get());
    }

    public static<T> CustomeGeneric<T> produce1(Supplier<T> s) {
        return new CustomeGeneric<T>(s.get());
    }

    public static void testPE(Supplier<Fruit> s) {
        System.out.println(s.get());
    }

    public static void testPE1(Supplier<? extends Fruit> s) {
        System.out.println(s.get());
    }
    public static void testPE2(Supplier<Apple> s) {
        System.out.println(s.get());
    }

    public static void testListPE(List<Fruit> s) {
        System.out.println(s.get(0));
    }

    public static void testListPE1(List<? extends Fruit> s) {
        System.out.println(s.get(0));
    }


    public void consume(Consumer<? super T> c) {
        c.accept(key);
    }

//        public static<T> CustomeGeneric<T> generate2(Supplier<? super T> s) {
//            return new CustomeGeneric<T>(s.get());
//        }
}

