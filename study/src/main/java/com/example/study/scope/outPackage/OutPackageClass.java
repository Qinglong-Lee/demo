package com.example.study.scope.outPackage;

import com.example.study.scope.inPackage.ClassScope;
import com.example.study.scope.inPackage.InterfaceScope;

public class OutPackageClass implements InterfaceScope {
    public void call() {
        ClassScope classScope = new ClassScope();
//        classScope.protectedFunc();
    }

    @Override
    public void noScopeFunc() {
        this.defaultFunc();
    }

    public static void main(String[] args) {
        OutPackageClass outPackageClass = new OutPackageClass();
        outPackageClass.noScopeFunc();
        outPackageClass.defaultFunc();
    }
}
