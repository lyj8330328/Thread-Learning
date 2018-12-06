package com.thread.basic;

/**
 * @Author: 98050
 * @Time: 2018-12-03 17:37
 * @Feature: 线程创建方式二
 */
public class Test002 {
    /**
     * 1.实现runable接口，重写run方法
     */

    public static void main(String[] args) {
        System.out.println("main...........主线程开始................");
        //1.创建线程
        ThreadDemo02 threadDemo02 = new ThreadDemo02();
        Thread thread = new Thread(threadDemo02);
        //2.启动线程
        thread.start();

        for (int i = 0; i < 10; i++) {
            System.out.println("main.......i:"+i);
        }
        System.out.println("main...........主线程结束................");
    }
}

class ThreadDemo02 implements Runnable{

    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("thread.............i:"+i);
        }
    }
}
