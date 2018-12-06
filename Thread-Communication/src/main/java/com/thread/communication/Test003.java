package com.thread.communication;

/**
 * @Author: 98050
 * @Time: 2018-12-06 15:03
 * @Feature:
 */
public class Test003 {
    public static void main(String[] args) {
        Thread2 thread2 = new Thread2();
        Thread1 thread1 = new Thread1(thread2);
        thread1.start();
        thread2.start();
    }
}

class Thread1 extends Thread{

    private final Thread2 thread2;

    public Thread1(Thread2 thread2) {
        this.thread2 = thread2;
    }

    @Override
    public void run() {
        System.out.println("进入线程1");
        synchronized (thread2){
            System.out.println("thread2 synchronized begin");
            long start = System.currentTimeMillis();
            try {
                System.out.println("thread2 wait begin");
                thread2.wait();
                System.out.println("thread2 wait over,cost："+(System.currentTimeMillis() - start) / 1000 + "秒");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}

class Thread2 extends Thread{

    @Override
    public void run() {
        System.out.println("进入线程2");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

