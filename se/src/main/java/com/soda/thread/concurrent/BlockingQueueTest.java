package com.soda.thread.concurrent;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueueTest {

    public static void main(String[] args) throws InterruptedException {

        LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>(3);


/*
        new Thread(()->{
            for (int i = 0; i < 4; i++) {

                queue.add(i);
            }
        }).start();
*/

        new Thread(() -> {

            try {
                for (int i = 0; i < 4; i++) {

                    queue.put(i);
                    System.out.println("insert:" + i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        TimeUnit.SECONDS.sleep(2);

        new Thread(() -> {

            try {
                for (int i = 0; i < 4; i++) {
                    Integer take = queue.take();
                    System.out.println("take:" + take);
                }
            } catch (Exception e) {
            }

        }).start();

    }
}
