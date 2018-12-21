package com.juc.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * @Author: 98050
 * @Time: 2018-12-20 20:50
 * @Feature: CountDownLatch的使用
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(2);

        new Thread(new Runnable() {
            public void run() {
                System.out.println(Thread.currentThread().getName()+",子线程开始执行");
                /**
                 * 计数器的值减一
                 */
                countDownLatch.countDown();
                System.out.println(Thread.currentThread().getName()+",子线程执行结束");
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                System.out.println(Thread.currentThread().getName()+",子线程开始执行");
                /**
                 * 计数器的值减一
                 */
                countDownLatch.countDown();
                System.out.println(Thread.currentThread().getName()+",子线程执行结束");
            }
        }).start();
        /**
         * 计数器值为0，恢复任务继续执行
         */
        countDownLatch.await();

        System.out.println("两个子线程执行完毕，主线程继续执行");
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName()+","+i);
        }
    }
}
