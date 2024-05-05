package com.soda.lambda;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.*;

public class MethodRefTest {

    public static void main(String[] args) {

        //静态方法引用
        BiFunction<Integer, Integer, Integer> sumFunc = Integer::sum;
        System.out.println(sumFunc.apply(5, 7));


        //成员方法签名参数是否可以省略this。

        //1.成员方法引用1
        MySum mySum = new MySum();
        BiFunction<Integer, Integer, Integer> sumFunc1 = mySum::decr;
        System.out.println(sumFunc1.apply(6, 3));


        //2.成员方法引用2,
        MySum mySum1 = new MySum();
        BiFunction<MySum, Integer, Integer> sumFunc2 = MySum::insMethod;
        System.out.println(sumFunc2.apply(mySum, 2));


        //构造方法引用
        Supplier<MySum> sumSupplier = MySum::new;
        Function<String, MySum> function = MySum::new;
        Function<Integer, MySum> function2 = MySum::new;

        System.out.println("default constructor: " + "a：" + sumSupplier.get().a + " c：" + sumSupplier.get().c);
        System.out.println("my constructor: " + "a：" + function2.apply(5).a  + " c：" + function2.apply(5).c);
        System.out.println("my constructor: " + "a：" + function.apply("hello").a  + " c：" + function.apply("hello").c);


        //数组构造器
        IntFunction<MySum[]> arrayMaker = MySum[]::new;
        MySum[] array = arrayMaker.apply(10);


    }


}


class MySum<T> implements Serializable {

    T c;

    public MySum(T c) {
        this.c = c;
    }


    public MySum(Integer a) {
        this.a = a;
    }

    public MySum() {
//        System.out.println("empty constructor");
    }

    Integer a = 4;

    public Integer decr(Integer b, Integer c) {

        return this.a + b + c;
    }


    public  Integer insMethod(Integer integer) {

        return this.a + integer;
    }
}


