package com.thread.security;

/**
 * @Author: 98050
 * @Time: 2018-12-03 23:04
 * @Feature: 静态同步函数
 */
public class Test003 {

    /**
     * 方法上加上static关键字，使用synchronized 关键字修饰 或者使用类.class文件。
     * @param args
     */
    public static void main(String[] args) {
        ThreadDemo3 threadDemo01 = new ThreadDemo3();
        Thread thread1 = new Thread(threadDemo01,"窗口1");
        Thread thread2 = new Thread(threadDemo01,"窗口2");
        thread1.start();
        thread2.start();
    }

}

class ThreadDemo3 implements Runnable{

    private static int count = 100;  //静态变量存放在方法区，所有线程共享

    private static Object object = new Object();

    public void run() {
        while (count > 0){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sale2();
        }
    }


    /**
     * 静态同步方法
     *
     * 静态的同步函数使用的锁是  该函数所属字节码文件对象
     */
    public static void sale(){
        synchronized (ThreadDemo3.class){
            if (count > 0){
                System.out.println(Thread.currentThread().getName() + ",出售"+(100-count+1)+"张票");
                count --;
            }
        }
    }

    public static synchronized void sale2(){
        if (count > 0){
            System.out.println(Thread.currentThread().getName() + ",出售"+(100-count+1)+"张票");
            count --;
        }

    }
}
