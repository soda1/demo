package com.soda.thread;

/**
 * 测试wait是否会释放锁
 */
public class WaitTest {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new MyRunnable());
        Thread thread1 = new Thread(new MyRunnable2());
        thread.start();
        Thread.sleep(2000);
        thread1.start();
    }

}



class MyRunnable implements Runnable {


    public void run() {

        SyncClass syncClass = new SyncClass();
        syncClass.method1();

    }
}

class MyRunnable2 implements Runnable {


    public void run() {

        SyncClass syncClass = new SyncClass();
        syncClass.method2();

    }
}




class SyncClass {

    public static Object object = 0;

    public void method1() {
        synchronized (object) {
            try {
                System.out.println("10s后释放锁");
                Thread.sleep(10000);

                object.wait();
                System.out.println("Thread continued");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void method2() {

        synchronized (object) {

            System.out.println("get the syncLock");
            object.notify();
        }

    }
}
