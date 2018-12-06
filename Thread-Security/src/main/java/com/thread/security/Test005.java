package com.thread.security;

/**
 * @Author: 98050
 * @Time: 2018-12-04 10:35
 * @Feature: 产生死锁
 */
public class Test005 {
    /**
     * t1 先获取obj锁，再获取this锁
     * t2 先获取this锁，再获取obj锁
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        ThreadDemoTest2 threadDemo = new ThreadDemoTest2();
        Thread thread1 = new Thread(threadDemo,"窗口1");
        Thread thread2 = new Thread(threadDemo,"窗口2");
        thread1.start();
        Thread.sleep(10);
        threadDemo.flag = false;
        thread2.start();
    }
}
class ThreadDemoTest2 implements Runnable {

    private static int count = 100;
    private static Object obj = new Object();
    public  boolean flag = true;

    public void run() {
        if (flag) {
            //使用同步锁this
            while (count > 0) {
                synchronized (obj) {
                    sale();
                }
            }
        } else {
            //使用静态同步函数
            while (count > 0) {
                sale();
            }
        }
    }

    public synchronized  void sale() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (obj) {
            if (count > 0) {
                System.out.println(Thread.currentThread().getName() + ",出售" + (100 - count + 1) + "张票");
                count--;
            }
        }
    }
}