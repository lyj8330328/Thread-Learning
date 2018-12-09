package com.thread.myfuture;

interface Callable {
    void call(int num);
}
public class FutureTest {


    public static void main(String[] args)  {

        System.out.println("主线程开始");

        final Callable callable = new Callable() {
            @Override
            public void call(int num) {
                System.out.println("线程运行结果值 num=="+num);
            }
        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程"+Thread.currentThread().getName()+" 开始");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                callable.call(100);
                System.out.println("线程"+Thread.currentThread().getName()+" 结束");
            }
        }, "t1").start();

        System.out.println("主线程结束");

    }
}
