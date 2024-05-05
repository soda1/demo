package com.soda.generics;

import java.lang.reflect.Method;

public class Pair<T> {


    private T element;

    public Pair(T element) {
        this.element = element;
    }

    public T getElement() {
        return this.element;
    }

    public void setElement(T element) {
        this.element = element;
    }



    public static void main(String[] args) throws NoSuchMethodException {
        Pair<String> hello = new Pair<>("hello");
        //编译阶段进行强转：String element = (String)hello.getElement();
        String element = hello.getElement();
        System.out.println(hello.getElement());

        Class<Pair> pairClass = Pair.class;
        Method getElement = pairClass.getMethod("getElement", null);
        System.out.println(getElement.getReturnType());
    }
}
