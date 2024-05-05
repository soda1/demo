package com.soda.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * 学习实现collector
 * @author JoannaLi
 * @param <T>
 * @param <A>
 * @param <R>
 */
public class CollectorTest<T,A,R>  implements Collector<T,A,R> {

    private Supplier<A> supplier;
    private BiConsumer<A, T> accumulator;
    private BinaryOperator<A> combiner;

    public CollectorTest(Supplier<A> supplier, BiConsumer<A, T> accumulator, BinaryOperator<A> binaryOperator) {
        this.supplier  = supplier;
        this.accumulator = accumulator;
        this.combiner = binaryOperator;
    }


    @Override
    public Supplier<A> supplier() {
        return supplier;
    }

    @Override
    public BiConsumer<A, T> accumulator() {
        return accumulator;
    }

    @Override
    public BinaryOperator<A> combiner() {
        return combiner;
    }

    @Override
    public Function<A, R> finisher() {
        return null;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return null;
    }


    public static void main(String[] args) {

        CollectorTest<Student, List<Student>, List<Student>> collectorTest =
                new CollectorTest<>(ArrayList::new, List::add, (a1, a2) -> {
                    a1.addAll(a2);
                    return a1;
                });

        List<Student> list = new ArrayList<>();
        list.add(new Student(17, "lisi"));
        list.add(new Student(17, "wangwu"));
        List<Student> collect = list.stream().collect(collectorTest);
        System.out.println(collect);
    }


}



