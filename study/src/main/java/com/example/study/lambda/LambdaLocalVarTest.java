package com.example.study.lambda;


import java.util.concurrent.TimeUnit;

/**
 * 测试匿名内部类对于【局部变量】的 final 限制
 * 如果传入匿名内部类的变量是方法的局部变量，则必须使用 final 修饰符，保证变量不可变。或者为【effectively final】【在行为上保证没有变化】
 * 对于类的全局变量或静态变量没有此限制
 *
 * 原理：
 * 方法的局部变量的生命周期是方法的作用域，即从方法结束局部变量也被销毁
 * 匿名内部类是【行为参数化】的一种实现方式，lambda也是一样，是将一个行为作为参数传递到某个方法，最终被执行
 * java 的传参类型是值传递，即对于基础类型，传递的是变量值的拷贝，对于引用类型，传递的是变量指向的对象地址的拷贝，两个都不是传递的变量本身
 * 因此对于局部变量：匿名内部类中的变量和外部方法中的变量并不是同一个，两者互不影响，即任何一个变量指向的数据的更改无法影响另一个变量的数据
 * 那么如果两个变量可以被更新，就会造成代码逻辑的混乱：开发这以为自己更新了变量，另一个也能同步更新，其实并没有
 * 因此 java 在编译层面强制要求传入匿名内部类的局部变量必须是 final 或 effectively final
 * 这样就在语法层面强调了【局部变量在匿名内部类内部和外部方法之间不应该做更新操作】
 *
 * 注意：对于引用类型，更改引用对象的属性值是允许的，不允许的是更改引用类型变量指向的对象地址
 * 因为更改引用对象的属性值是可以影响到其他同样指向当前对象的变量的
 *
 * 而对于全局变量，静态变量就不存在这个问题
 * 对于全局变量，在编译期生成匿名内部类的时候会将当前对象传递进去，因此内部使用的变量也就是当前对象的变量，是同一个
 * 对于静态变量，本身就全局可见，匿名内部类中也直接引用即可，无需传参，因此内外也都是同一个变量
 */
public class LambdaLocalVarTest {
    public static int global = 1;

    public static void main(String[] args) throws InterruptedException {
        final Object obj = new Object();
        final int str = 1;
        Thread t = new Thread() {
            @Override
            public void run() {
//                obj = null;
//                System.out.println(obj);
//                System.out.println(str);
                System.out.println(global);
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
//                System.out.println(str);
                System.out.println(global);
                global = 3;

            }
        };
        t.start();
        TimeUnit.SECONDS.sleep(1);
        global = 2;
        t.join();
        System.out.println(global);

    }
}
