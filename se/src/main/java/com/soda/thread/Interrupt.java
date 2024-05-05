package com.soda.thread;

import java.util.Timer;
import java.util.concurrent.TimeUnit;

/**
 * @author soda
 * @date 2022/4/5
 * 线程中断
 */
public class Interrupt {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("running");
            }
            System.out.println("finish");
        }, "thread-1");
        thread.start();
        TimeUnit.SECONDS.sleep(2);
        System.out.println(thread.isInterrupted());
        thread.interrupt();
    }
}
