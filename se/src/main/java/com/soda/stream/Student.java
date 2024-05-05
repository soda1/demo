package com.soda.stream;

import java.util.ArrayList;
import java.util.List;

/**
 * @author soda
 * @date 2021/8/1
 */
public class Student {
    int age;

    String name;

    public void setAge(int age) {
        this.age = age;
    }



    public int getAge() {
        return age;
    }

    public Student(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    public static void main(String[] args) {
        System.out.println("start");
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            list.add(String.valueOf(i));
            new Thread(() -> {
                list.remove(0);
                System.out.println(Thread.currentThread().getName());
            }, "Thread" + i).start();
        }
    }
}
