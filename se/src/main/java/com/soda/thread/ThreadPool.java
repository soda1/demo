package com.soda.thread;

import java.util.concurrent.*;

/**
 * Executors工具类简单使用
 */
public class ThreadPool {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        //runnable接口
        executorService.execute(() ->{
            System.out.println(Thread.currentThread().getName());
        });
        executorService.execute(() ->{
            System.out.println(Thread.currentThread().getName());
        });


        executorService.execute(() ->{
            try {
                //验证main线程在执行shutdown方法后，是不是等待线程执行完毕后才会往下执行
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        });

        //callable接口
        Future str = executorService.submit(() ->{
            Thread.sleep(5000);
            return "hello";
        });

        //调用get方法会阻塞当前线程，直到获取结果
        System.out.println(str.get());
        executorService.shutdown();
        System.out.println("任务完成");


    }

     class Mythread implements Callable {
         @Override
         public Object call() throws Exception {
             return null;
         }
     }


}
