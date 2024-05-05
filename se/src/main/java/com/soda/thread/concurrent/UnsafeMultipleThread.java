package com.soda.thread.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * 多线程并发下，会得到多种结果，测试代码期待结果为0
 */
public class UnsafeMultipleThread {


    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(3);

        Book book = new Book(30000);

        new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                book.borrowBook(1);
            }
            countDownLatch.countDown();
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                book.borrowBook(1);
            }
            countDownLatch.countDown();
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                book.borrowBook(1);
            }
            countDownLatch.countDown();
        }, "B").start();

        countDownLatch.await();

        System.out.println(Thread.currentThread().getName() + " book left:" + book.num);
    }

}


class Book {

    volatile int num;

    public Book(int num) {
        this.num = num;
    }


    public void borrowBook(int num) {
        try {
            this.num -= num;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }


}