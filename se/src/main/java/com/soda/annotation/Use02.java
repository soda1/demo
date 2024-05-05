package com.soda.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class Use02 extends MyAnnotationUse {

  /*  @Override
    public void method1() {
        System.out.println("hello");
    }*/

    public static void main(String[] args) throws NoSuchMethodException {
        Class<Use02> use02Class = Use02.class;

        MyAnnotation annotation = use02Class.getAnnotation(MyAnnotation.class);
        System.out.println(annotation.value());

    }
}
