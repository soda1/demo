package com.soda.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class FourFunctionInterface {


    public static void main(String[] args) {


        Supplier supplier = (Supplier<ArrayList<String>>) ArrayList::new;

        List<String> list1 = (List<String>) supplier.get();

        list1.add("hello");
        System.out.println(list1.toString());

    }




}
