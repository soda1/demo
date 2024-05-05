package com.soda.classloader;


/**
 * test static block and static var execution order
 */
public class StaticBlock {

    public static void main(String[] args) {
        A a = new A();

        System.out.println("m:" +  a.m);
        System.out.println("n:" + a.n);
    }

}

class A {


    static int m = 100;


    static {
        System.out.println("start");
        m = 300;
        n = 300;
        System.out.println(A.m);
        System.out.println(A.n);
        System.out.println("static block finish");
    }
    static int n = 100;


    public void say() {
        System.out.println("hello");
    }

}
