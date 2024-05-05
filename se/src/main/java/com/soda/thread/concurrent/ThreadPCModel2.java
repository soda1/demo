package com.soda.thread.concurrent;

/**
 * 生产者消费者模型2：信号灯法
 */
public class ThreadPCModel2 {


    public static void main(String[] args) throws InterruptedException {

        TV tv = new TV();
        new Player(tv).start();
        new Watcher(tv).start();
    }

}

class Player extends  Thread{

    TV tv;

    public Player(TV tv) {
        this.tv = tv;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0) {
                try {
                    this.tv.TvShow("不能说的秘密第" + i + "集");
                    System.out.println("演员正在录制: 不能说的秘密第" + i + "集");
//                    System.out.println("演员正在录制: 不能说的秘密");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    this.tv.TvShow("牛皮藓广告");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


class Watcher extends Thread {

    TV tv;

    public Watcher(TV tv) {
        this.tv = tv;
    }

    @Override
    public void run()  {
        for (int i = 0; i < 20; i++) {

            try {
                //防止线程执行太快导致输出看起来不符合逻辑
             sleep(1000);
                System.out.println("观众正在观看：" + this.tv.WatchTV());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


class TV{

    String voice = null;

    // 演员表演节目：F
    //观众观看节目：T
 boolean singleFlag = false;

    public synchronized void TvShow(String voice) throws InterruptedException {

        if (this.singleFlag == true) {
            this.wait();
        }

        this.voice = voice;
        this.singleFlag = !this.singleFlag;
        this.notifyAll();
    }


    public synchronized String WatchTV() throws InterruptedException {
        if (this.singleFlag == false) {
            this.wait();
        }
        this.notifyAll();
        this.singleFlag = !this.singleFlag;
        return this.voice;

    }
}



