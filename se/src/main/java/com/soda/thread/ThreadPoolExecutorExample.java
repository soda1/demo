package com.soda.thread;

import java.util.concurrent.*;

public class ThreadPoolExecutorExample {

    public static void main(String[] args) {
        int coreSize = 5;
        int maximumSize = 10;
        int keepLongLiveTime = 1;
        int workCapacity = 10;
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(coreSize, maximumSize, keepLongLiveTime, TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(workCapacity), new ThreadPoolExecutor.CallerRunsPolicy());

        for (int i = 0; i < 30; i++) {
            threadPoolExecutor.execute(() ->{
                System.out.println(Thread.currentThread().getName());
            });
        }

    }
}
