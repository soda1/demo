package com.soda.thread;

import java.util.concurrent.TimeUnit;

public class ThisEscape {

    final int i;
    public ThisEscape(Escape escape) {
        escape.getEscape(this);
        new Thread(() -> {
            System.out.println(escape.thisEscape.getI());
        }).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        i = 6;
    }

    public static void main(String[] args) throws InterruptedException {
        Escape escape = new Escape();
        ThisEscape thisEscape = new ThisEscape(escape);
        System.out.println(escape.thisEscape.getI());

    }

    public int getI() {
        return i;
    }
}


class Escape{

     int i;
     ThisEscape thisEscape;
    public Escape()  {

    }

    public void getEscape(ThisEscape thisEscape) {
        this.thisEscape = thisEscape;
    }
}
