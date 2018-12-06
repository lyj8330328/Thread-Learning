package com.thread.basic;

/**
 * @Author: 98050
 * @Time: 2018-12-03 17:14
 * @Feature: 线程创建方式一
 */
public class Test001 {
    /**
     * 1.线程是一条执行路径，每个线程都互不影响
     * 2.多线程在一个进程中有多条不同的执行路径，并行执行，目的是为了提高程序的效率
     * 3.在一个进程中，一定会有主线程
     * 4.线程分为：用户线程、守护线程
     * 5.主线程、子线程、GC线程
     */
    public static void main(String[] args) {
        System.out.println("main...........主线程开始................");
        //1.创建线程
        ThreadDeom01 threadDeom01 = new ThreadDeom01();
        //2.启动线程
        threadDeom01.start();

        for (int i = 0; i < 10; i++) {
            System.out.println("main.......i:"+i);
        }
        System.out.println("main...........主线程结束................");

    }
}

class ThreadDeom01 extends Thread{

    @Override
    public void run() {
        for (int i = 0; i < 10; i ++){
            System.out.println("thread.........i:"+i);
        }
    }
}
