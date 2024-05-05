package com.soda.thread;

import static java.lang.Thread.sleep;
import static java.lang.Thread.yield;

public class CreateThread {

    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
        myThread.start();

        Thread thread = new Thread(new MyRunable());
        thread.start();
        //join使用
        for (int i = 0; i < 50; i++) {
            if (i == 30) {
                thread.join();

            }
        }
        System.out.println("main finish");

    }
}

class MyThread extends Thread{

    @Override
    public void run() {
        System.out.println("thread start");
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("thread end");
    }
}

class MyRunable implements Runnable {

    public void run() {
        System.out.println("thread start");
        try {
            sleep(2000);
            for (int i = 0; i < 100; i++) {
                if (i % 10 == 0) {
                    System.out.println("yield");
                    Thread.yield();
                    //线程状态
                    System.out.println(Thread.currentThread().getState());

                }
                System.out.println(i);
            }
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("thread end");
    }
}
