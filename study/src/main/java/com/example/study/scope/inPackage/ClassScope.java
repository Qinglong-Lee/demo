package com.example.study.scope.inPackage;


/**
 * 类定义的作用域只有两个：
 * public：所有类都可以实现此接口
 * friendly：默认作用域，仅支持包内的类实现此接口
 * friendly 和 protected 的区别是：
 * friendly 只能用于类或接口的定义，不允许包外任何的访问，包括实现类或子类
 * protected 只能用于字段或方法，且允许包外的子类访问
 */
public class ClassScope implements InterfaceScope{
    /**
     * 类字段默认作用于是【protected】
     */
    String filed;
    @Override
    public void noScopeFunc() {
        System.out.println(InterfaceScope.hello);

    }

    /**
     * 类中方法的默认作用域是【protected】
     */
    void protectedFunc() {
        System.out.println("I'm a protectd Func!");
    }

    void callSupeDefaultFunc() {
        this.defaultFunc();
    }

    public static void main(String[] args) {
        ClassScope classScope = new ClassScope();
        classScope.noScopeFunc();
        classScope.noScopeFunc();
        classScope.defaultFunc();
    }
}
