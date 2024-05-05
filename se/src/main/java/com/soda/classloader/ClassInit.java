package com.soda.classloader;

/**
 * 类构造器函数什么时候会被初始化
 */
public class ClassInit {

    public static void main(String[] args) throws ClassNotFoundException {
        //调用父类变量初始化情况
        System.out.println(B1.m);
        //定义数组初始化情况
        B[] bs = new B[4];
        //反射调用类
        Class.forName("com.soda.classloader.B");
    }


}

class B{

    public static int m = 100;
    public final  static int i = 1;
    static {
        System.out.println("B class init....");
    }
}


class B1 extends B{

    static {
        System.out.println("B1 class init ....");
    }
}

