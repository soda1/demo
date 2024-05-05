package com.soda.annotation;


import java.lang.annotation.*;

@Target(value = {ElementType.METHOD, ElementType.TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
@Documented()
@Inherited()
public @interface MyAnnotation {

    //定义参数
    String value();
}
