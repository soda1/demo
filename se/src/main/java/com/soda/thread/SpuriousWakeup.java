package com.soda.thread;

/**
 * 1.0：使用wait方法时，会存在这虚假唤醒的情况，如果使用if判断wait方法调用条件, 由于判断条件只执行依次，
 * 所以有可能导致程序的结果出错(多个线程一起唤醒争抢锁），官方推荐使用while
 *
 * 2.0 错误的理解，虚假唤醒和底层相关，测试代码模型不对。通过唤醒单一线程是无法模拟虚假唤醒，只能说明由于你顺序两次唤醒的都是
 * 相同任务的线程，可以通过使用while来解决。
 */
public class SpuriousWakeup {


    public static void main(String[] args) {
        DataOperation dataOperation = new DataOperation();

        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    dataOperation.incr();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();


        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    dataOperation.incr();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "B").start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    dataOperation.decr();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "C").start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    dataOperation.decr();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "D").start();
    }


}

class DataOperation {

    int num = 0;


    public synchronized void incr() throws InterruptedException {

        if (num != 0) {
            this.wait();
        }
/*

        while (num != 0) {
            this.wait();
        }
*/


        num++;
        System.out.println(Thread.currentThread().getName() + ":num=" + num);
        this.notify();
    }

    public synchronized void decr() throws InterruptedException {

        if (num == 0) {
            this.wait();
        }
/*
        while (num == 0) {
            this.wait();
        }
*/
        num--;
        System.out.println(Thread.currentThread().getName() + ":num=" + num);
        this.notify();
    }

}
