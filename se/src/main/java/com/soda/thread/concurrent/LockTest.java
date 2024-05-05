package com.soda.thread.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * lock锁简单使用
 */
public class LockTest {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(3);

        Book1 book1 = new Book1();

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                int i1 = book1.borrowBook();

            }
            countDownLatch.countDown();
        }, "A").start();

        new Thread(() -> {
            for (int i = 1; i < 101; i++) {
                book1.buyBook(i);

            }
            countDownLatch.countDown();
        }, "B").start();


        countDownLatch.await();

        System.out.println(Thread.currentThread().getName() + " book1 left:" + book1.num);
    }

}


class Book1 {

    int[] num = new int[20];

    int count=0;

    Lock lock = new ReentrantLock();

    Condition condition1 = lock.newCondition();



    public int borrowBook() {

        try {
            lock.lock();
            if (count == 0) {
                condition1.await();
            }
            count --;

            int i = this.num[count];

            condition1.signal();
            System.out.println("bookorder: " + i);
            return i;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return 0;

    }

    public void  buyBook(int i) {

        try{
            lock.lock();
            if (count == this.num.length) {
                condition1.await();
            }
            this.num[count] = i;
            count ++;
            condition1.signal();
            System.out.println("buybookorder: " + i);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }



}



