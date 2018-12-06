package com.thread.security;

/**
 * @Author: 98050
 * @Time: 2018-12-03 21:11
 * @Feature: 非静态同步方法、同步代码块解决线程安全问题
 */
class ThreadDemo01 implements Runnable{

    //private int count = 100;
    private static int count = 100;  //静态变量存放在方法区，所有线程共享

    //private Object object = new Object();
    private static Object object = new Object();

    public void run() {
        while (count > 0){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sale();
        }
    }

    //非静态同步方法
//    public synchronized void sale(){
//        if (count > 0){
//            System.out.println(Thread.currentThread().getName() + ",出售"+(100-count+1)+"张票");
//            count --;
//        }
//    }

    /**
     * 同步代码块
     */
    public  void sale(){
        synchronized (object){
            if (count > 0){
                System.out.println(Thread.currentThread().getName() + ",出售"+(100-count+1)+"张票");
                count --;
            }
        }
    }
}

/**
 * 解决线程安全问题：
 * 内置锁：Synchronized 保证线程原子性，当线程进入方法的时候，自动获取锁，一旦锁被其它线程获取到后，其它线程就会等待
 * 锁的特征：只能有一个线程进行使用。
 * 释放锁：程序执行完毕后，就会把锁释放。
 * 会降低程序运行效率。
 * 锁的资源竞争。 重入锁
 * 内置锁也是互斥锁
 * 使用方式：同步方法、同步代码
 * 显示锁
 */

/**
 * 使用同步的两种方式：
 * 同步代码块
 * 同步方法：修饰在方法上的
 *      非静态同步方法
 *      静态同步方法
 * 使用同步的时候，锁一定是相同的
 */
public class Test001 {

    public static void main(String[] args) {
        ThreadDemo01 threadDemo01 = new ThreadDemo01();
        Thread thread1 = new Thread(threadDemo01,"窗口1");
        Thread thread2 = new Thread(threadDemo01,"窗口2");
        thread1.start();
        thread2.start();
    }
}
