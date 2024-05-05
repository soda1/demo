package com.soda.thread;

import java.util.concurrent.TimeUnit;

/**
 * 验证内存不可见性、volatile关键字作用
 * System.out.println(volatileTest.i);会刷新缓存要注意
 */
public class VolatileTest {

//   volatile int i = 0;
     int i = 0;

    public static void main(String[] args) throws InterruptedException {

        VolatileTest volatileTest = new VolatileTest();
        new Thread(() -> {

                try {
                    TimeUnit.SECONDS.sleep(1);
                    volatileTest.i = 9;
                    System.out.println(volatileTest.i);
                } catch (InterruptedException e) {
                    e.printStackTrace();

            }
        }).start();
        new Thread(() -> {

            while (true) {
//                System.out.println("hello");
//                System.out.println(a.isFlage());
                if (volatileTest.i != 0) {
                    System.out.println(volatileTest.i);
                    break;
                }
            }
        }).start();



    }


}


class A {
    private int i =  0;
    private boolean flage = false;
    public void setI(int i) {
        this.i = i;
    }

    public int getI() {
        return i;
    }

    public void setFlage(boolean flage) {
        this.flage = flage;
    }

    public boolean isFlage() {
        return flage;
    }
}
