package com.soda.class1;


/**
 * 测试private修饰内部类
 */
public class Test1 {

    public static void main(String[] args) {
        NonStaticInnerClassTest nonStaticInnerClassTest = new NonStaticInnerClassTest();

        NonStaticInnerClassTest.MyInnerClass myInnerClass = nonStaticInnerClassTest.new MyInnerClass();

        getInnerClass();

    }


       public static void getInnerClass() {
        StaticInnerClassTest.MyStaticInnerClass staticInnerClass = new StaticInnerClassTest.MyStaticInnerClass();
        staticInnerClass.getOuterStatic();

    }

}
