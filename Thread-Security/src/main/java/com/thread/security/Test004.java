package com.thread.security;

/**
 * @Author: 98050
 * @Time: 2018-12-03 23:10
 * @Feature: 证明静态同步方法使用的是该函数所属字节码文件对象
 */
public class Test004 {

    public static void main(String[] args) throws InterruptedException {
        ThreadDemoTest threadDemo = new ThreadDemoTest();
        Thread thread1 = new Thread(threadDemo,"窗口1");
        Thread thread2 = new Thread(threadDemo,"窗口2");
        thread1.start();
        Thread.sleep(40);
        threadDemo.flag = false;
        thread2.start();
    }
}

class ThreadDemoTest implements Runnable{

    private static int count = 100;
    public  boolean flag = true;

    public void run() {
        if (flag){
            //使用同步锁this
            while (count > 0){
                sale();
            }
        }else {
            //使用静态同步函数
            while (count > 0){
                sale2();
            }
        }
    }

    public void sale(){
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //使用this，两个线程无法同步
        //使用ThreadDemoTest.class可以同步
        synchronized (this) {
            if (count > 0) {
                System.out.println(Thread.currentThread().getName() + ",出售" + (100 - count + 1) + "张票");
                count--;
            }
        }
    }

    public synchronized static void sale2(){
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (count > 0) {
                System.out.println(Thread.currentThread().getName() + ",出售" + (100 - count + 1) + "张票");
                count--;
            }
    }
}