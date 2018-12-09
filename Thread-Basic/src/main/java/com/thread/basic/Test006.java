package com.thread.basic;

/**
 * @Author: 98050
 * @Time: 2018-12-03 19:45
 * @Feature:
 */
public class Test006 {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("main...........主线程开始................");
        //1.创建线程
        ThreadDemo3 threadDemo3 = new ThreadDemo3();
        Thread thread = new Thread(threadDemo3);
        //2.启动线程
        thread.start();
        //需要让子线程先执行完毕
        thread.join();
        for (int i = 0; i < 30; i++) {
            System.out.println("主线程,i:"+i);
        }
        System.out.println("main...........主线程结束................");
    }
}

class ThreadDemo3 implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 60 ; i++) {
            System.out.println("子线程,i:"+i);
        }
    }
}
