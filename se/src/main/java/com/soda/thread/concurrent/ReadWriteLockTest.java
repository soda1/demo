package com.soda.thread.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest {
    List<String> list = new ArrayList<>();
    ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    public static void main(String[] args) throws InterruptedException {

        ReadWriteLockTest readWriteLockTest = new ReadWriteLockTest();
        readWriteLockTest.write2Case();
        TimeUnit.SECONDS.sleep(3);
        readWriteLockTest.read2Case();
        TimeUnit.SECONDS.sleep(3);
        readWriteLockTest.readWriteCase();
    }

    //测试读锁
    public void read2Case() {

        for (int i = 0; i < 2; i++) {
            readThread(i, readWriteLock.readLock());
        }

    }

    //测试写读锁同时作用
    public void readWriteCase() {

        writeThread(1, readWriteLock.writeLock());
        readThread(2, readWriteLock.readLock());

    }

    //测试写锁
    public void write2Case() {


        for (int i = 0; i < 2; i++) {
            writeThread(i, readWriteLock.writeLock());
        }
    }

    private void writeThread(int i, Lock lock) {
        new Thread(() -> {
            try {
                lock.lock();
                System.out.println("开始写入");
                list.add(UUID.randomUUID().toString().substring(0, 8));
//                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName() + "写入完成");
                lock.unlock();
            }

        }, String.valueOf(i)).start();
    }

    private void readThread(int i, Lock lock) {
        new Thread(() -> {
            try {
                lock.lock();
                System.out.println("开始读取");
                System.out.println(list);
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName() + "读取完成");
                lock.unlock();
            }

        }, String.valueOf(i)).start();
    }
}
