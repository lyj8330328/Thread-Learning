package com.thread.security;

/**
 * @Author: 98050
 * @Time: 2018-12-03 22:22
 * @Feature: 证明同步方法使用的是this锁
 */
public class Test002 {
    /**
     * 一个线程使用同步代码块(this明锁),另一个线程使用同步函数。如果两个线程抢票不能实现同步，那么会出现数据错误。
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        ThreadDemo2 threadDemo = new ThreadDemo2();
        Thread thread1 = new Thread(threadDemo,"窗口1");
        Thread thread2 = new Thread(threadDemo,"窗口2");
        thread1.start();
        Thread.sleep(40);
        ThreadDemo2.flag = false;
        thread2.start();
    }

}

class ThreadDemo2 implements Runnable{

    private int count = 100;
    private static Object object = new Object();
    public static   boolean flag = true;

    public void run() {
        if (flag){
            //使用同步锁this
            while (count > 0){
                sale2();
            }
        }else {
            //使用同步函数
            while (count > 0){
                sale();
            }
        }
    }

    public synchronized void sale(){
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (count > 0){
            System.out.println(Thread.currentThread().getName() + ",出售"+(100-count+1)+"张票");
            count --;
        }
    }

    public void sale2(){
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (this) {
            if (count > 0) {
                System.out.println(Thread.currentThread().getName() + ",出售" + (100 - count + 1) + "张票");
                count--;
            }
        }
    }
}
