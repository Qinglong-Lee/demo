package com.example.study.scope.outPackage;

import com.example.study.scope.inPackage.ClassScope;

public class OutPackageSubClass extends ClassScope {
    public void call() {
//        super.field = "";
//        super.protectedFunc();
    }

    public static void main(String[] args) {
        OutPackageSubClass outPackageClass = new OutPackageSubClass();
        outPackageClass.call();
    }
}
