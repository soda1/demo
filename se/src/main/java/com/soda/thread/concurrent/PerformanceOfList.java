package com.soda.thread.concurrent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;

/**
 * 比较synchronizedList和CopyOnWriteArrayList写操作花费时间
 * 比较synchronizedList和CopyOnWriteArrayList写操作花费时间
 */
public class PerformanceOfList {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch syncCountDown = new CountDownLatch(2);
        CountDownLatch copyCountDown = new CountDownLatch(2);

        List<Integer> list1 = Collections.synchronizedList(new ArrayList<>());

        List<Integer> list2 = new CopyOnWriteArrayList<>();

        long start = System.currentTimeMillis();
        new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                list1.add(i);
            }
            syncCountDown.countDown();

        }).start();

        new Thread(() -> {
            for (int i = 0; i < 20000; i++) {
                list1.add(i);
            }
            syncCountDown.countDown();

        }).start();
        syncCountDown.await();
        long end = System.currentTimeMillis();
        System.out.println("synchronizedList time operation: " + (end - start));


        long start1 = System.currentTimeMillis();
        new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                list2.add(i);
            }
            copyCountDown.countDown();

        }).start();

        new Thread(() -> {
            for (int i = 0; i < 20000; i++) {
                list2.add(i);
            }
            copyCountDown.countDown();

        }).start();
        copyCountDown.await();
        long end1 = System.currentTimeMillis();
        System.out.println("CopyOnWriteArrayList write time operation: " + (end1 - start1));
    }
}
