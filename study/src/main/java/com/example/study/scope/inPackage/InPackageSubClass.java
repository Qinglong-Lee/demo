package com.example.study.scope.inPackage;

public class InPackageSubClass extends ClassScope {
    public void call() {
        super.filed = "";
        super.protectedFunc();
    }

    public static void main(String[] args) {
        InPackageSubClass outPackageClass = new InPackageSubClass();
        outPackageClass.call();
    }
}
