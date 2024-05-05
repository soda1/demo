package com.soda.thread.concurrent;

/**
 * 生产者消费者模型：管道法
 */
public class ThreadPCModel {


    public static void main(String[] args) throws InterruptedException {

        SyncContainer syncContainer = new SyncContainer();
        new Product(syncContainer).start();

//        Thread.sleep(5000);
        new Consummer(syncContainer).start();
    }

}

//生产者线程
class Product extends Thread {

    SyncContainer syncContainer;

    public Product(SyncContainer syncContainer) {
        this.syncContainer = syncContainer;
    }

    @Override
    public void run() {
        int i = 0;
        //生产鸡
        while (true) {
            try {
                this.syncContainer.Push(new Chicken(syncContainer.sum + 1));
                System.out.println("生产了第" + this.syncContainer.sum + "只鸡");

                //生产完30只鸡就不生产了
                if (this.syncContainer.sum == 30) {
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}


class Consummer extends Thread {

    SyncContainer syncContainer;

    public Consummer(SyncContainer syncContainer) {
        this.syncContainer = syncContainer;
    }

    @Override
    public void run() {
        while (true) {
            try {

                Chicken pop = this.syncContainer.Pop();
                System.out.println("消费了第" + pop.id + "只鸡");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}


//产品
class Chicken {

    int id;

    public Chicken(int id) {
        this.id = id;
    }
}

//缓冲区
class SyncContainer {

    //缓冲区
    Chicken[] chicken = new Chicken[10];
    int count = 0;

    //生产鸡的总数
    int sum = 0;


    //生产鸡
    public synchronized void Push(Chicken chicken) throws InterruptedException {

//        System.out.println(this.chicken.length);
        if (count == this.chicken.length) {
            //缓冲区满了就不生产
            this.wait();
        }

        //生产鸡
        this.chicken[count] = chicken;
        count++;
        sum++;
        //通知消费者消费鸡
        this.notifyAll();
    }



    //消费鸡
    public synchronized Chicken Pop() throws InterruptedException {
        if (count == 0) {

            //消费者等待
            this.wait();
        }
        count--;
        Chicken chicken = this.chicken[count];
        this.chicken[count] = null;

        //消耗鸡了就通知生产者生产鸡
        this.notifyAll();
        return chicken;
    }

}

