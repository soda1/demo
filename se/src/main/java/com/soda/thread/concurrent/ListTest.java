package com.soda.thread.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
/**
 * 1、测试ArrayList线程不安全问题，元素出现null值
 * 2、解决ArrayList线程不安全方案
 */
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

public class ListTest {


    public static void main(String[] args) throws InterruptedException {


        List<String> strings = new ArrayList<>();

        //1、使用工具类的同步方法
//        List<String> strings = Collections.synchronizedList(new ArrayList<>());;

        //2、使用并发包中的CopyOnWriteArrayList类
//        List<String> strings = new CopyOnWriteArrayList<>();


        //多线程并发写值
        for (int i = 0; i < 3; i++) {

            new Thread(() -> {
                String substring = UUID.randomUUID().toString().substring(0, 8);
                strings.add(substring);
//                System.out.println(Thread.currentThread().getName() + " " + substring  + strings);
            }, String.valueOf(i)).start();
        }

        //等写操作完成后打印数组
        TimeUnit.SECONDS.sleep(10);
        System.out.println("数组size:" + strings.size());
        for (int i = 0; i < strings.size(); i++) {
            System.out.println("数组第" + i + "个元素" + strings.get(i));
        }

    }


}


