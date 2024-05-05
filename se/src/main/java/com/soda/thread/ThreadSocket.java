package com.soda.thread;

import java.util.ArrayList;
import java.util.List;

public class ThreadSocket {

    public volatile static  List<String> list = new ArrayList<String>();

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(
                () -> {
                    while (true) {
//                        System.out.println("生产线程状态：" + Thread.currentThread().getState());
//                        System.out.println(list.size());
                        if (list.size() == 0) {
                            System.out.println("empty");
                            synchronized (list) {
                                list.add("hello");
                                list.notify();
                                System.out.println("已经生产了");


                            }
                        }
                    }
                });

        Thread thread2 = new Thread(() -> {
            while (true) {
                synchronized (list) {
//                    System.out.println("消费线程状态：" + Thread.currentThread().getState());
                    if (list.size() != 0) {
                        list.remove(0);
                        System.out.println("已经消费了");
//                        System.out.println("消费线程状态：" + Thread.currentThread().getState());
                    } else {
                        try {
                            System.out.println("消费线程处于等待状态");
                            list.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        thread1.start();
        thread2.start();


        while (true) {
            Thread.sleep(5000);
            System.out.println("生产线程" + thread1.getState());
            System.out.println("消费线程" + thread2.getState());
            System.out.println(list.size());
        }
    }


}
