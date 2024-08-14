package com.example.study.scope.inPackage;

/**
 * 接口定义的作用域只有两个：
 * public：所有类都可以实现此接口
 * friendly：默认作用域，仅支持包内的类实现此接口
 */
public interface InterfaceScope {
//    接口中字段的默认作用域为【public static】
    String hello = "Hi";

//    接口中方法的默认作用域为【public】
    void noScopeFunc();

    /**
     * 私有方法必须有方法体
     * 因为私有方法只能在此接口的其他私有方法或默认方法被调用
     */
    private void privateFunc() {
        System.out.println("I'm a privateFunc");
    }

    /**
     * 默认方法必须有方法体
     * 默认方法没有包的限制
     */
    default void defaultFunc() {
        System.out.println("I'm a privateFunc");
        privateFunc();
    }
}
