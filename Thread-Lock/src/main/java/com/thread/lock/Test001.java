package com.thread.lock;

/**
 * @Author: 98050
 * @Time: 2018-12-13 16:29
 * @Feature: 证明synchronized是可重入锁
 */
public class Test001 implements Runnable{
    public void run() {
        get();
    }

    public synchronized void get(){
        System.out.println("name:"+Thread.currentThread().getName()+",get()");
        set();
    }

    public synchronized void set(){
        System.out.println("name:"+Thread.currentThread().getName()+",set()");
    }

    public static void main(String[] args) {
        Test001 test001 = new Test001();
        new Thread(test001).start();
        new Thread(test001).start();
        new Thread(test001).start();
        new Thread(test001).start();
    }
}
