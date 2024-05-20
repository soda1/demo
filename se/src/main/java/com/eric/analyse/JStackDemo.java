package com.eric.analyse;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author eric
 * @date 5/19/2024
 */
public class JStackDemo {

    public static void main(String[] args) {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(4, (runnable) -> new Thread(runnable, "myThread"));
        fixedThreadPool.submit(() -> {
            System.out.println("hi");
            try {
                TimeUnit.SECONDS.sleep(30);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } );
        while (true) {

        }

    }
}
