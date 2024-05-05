package com.soda.thread.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 *集齐七颗龙珠召唤神龙
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            //召唤神龙
            System.out.println("召唤神龙");
        });
        for (int i = 0; i < 7; i++) {
            final int ii = i + 1;
            //7个线程找龙珠
            new Thread(()->{

                try {
                    System.out.println("获取第" + ii + "颗龙珠");
                    cyclicBarrier.await();
                    System.out.println(Thread.currentThread().getName() + " 神龙已召唤");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }

    }
}
