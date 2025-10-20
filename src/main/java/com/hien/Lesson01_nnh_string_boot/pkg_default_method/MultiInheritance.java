package com.hien.Lesson01_nnh_string_boot.pkg_default_method;

interface Interface1 {
    default void method1() {
        System.out.println("Interface1.method1");
    }
}

interface Interface2 {
    default void method2() {
        System.out.println("Interface2.method2");
    }
}

public class MultiInheritance implements Interface1, Interface2 {
    @Override
    public void method1() {
        Interface1.super.method1(); // gọi từ Interface1
    }

    @Override
    public void method2() {
        Interface2.super.method2(); // gọi từ Interface2
    }

    public static void main(String[] args) {
        MultiInheritance obj = new MultiInheritance();
        obj.method1();
        obj.method2();
    }
}