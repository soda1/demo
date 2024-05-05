package com.soda.thread;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class CallableTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //需要使用FutureTask来封装才能丢入线程中执行
        FutureTask task = new FutureTask(() ->{
            System.out.println("2s后返回");
            TimeUnit.SECONDS.sleep(2);
            return "hello";
        });
        Thread thread = new Thread(task);
        thread.start();
        System.out.println(task.get());

    }
}


