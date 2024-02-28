package com.example.demo.objectInit;

public class Main
{
    public static void main(String[] args)
    {

//        进入Derived类构造函数
//        Derived成员变量的内存被分配
//        调用Base类的构造函数
//        Base类构造函数调用preProcess()方法
//        Derived类的preProcess()方法设置whenAmISet
//        Derived类的成员变量初始化被调用:public String whenAmISet = "set when declared";
//        执行Derived构造函数体
        Derived d = new Derived();
        System.out.println( d.whenAmISet );
    }
}