package com.soda.class1;

public class StaticInnerClassTest {

    private int i = 4;

    public static int c = 6;

     public static class  MyStaticInnerClass{
           int i = 0;

           public void method1() {
               System.out.println("hello");
           }

         public static void staticMethod() {
             System.out.println("static method");
         }

         public void getOuterStatic() {

             System.out.println(StaticInnerClassTest.c);

             StaticInnerClassTest.outerStaticMethod();

         }




    }

    public static void outerStaticMethod() {
        System.out.println("outer static method");
    }


    public static void main(String[] args) {

        MyStaticInnerClass myStaticInnerClass = new MyStaticInnerClass();
        myStaticInnerClass.getOuterStatic();
    }
}
