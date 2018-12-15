package com.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: 98050
 * @Time: 2018-12-13 16:43
 * @Feature: 证明lock锁是可重入锁
 */
public class Test002 extends Thread{

    Lock lock = new ReentrantLock();


    @Override
    public void run() {
        get();
    }

    private void get(){
        try {
            lock.lock();
            System.out.println("name:"+Thread.currentThread().getName()+",get()");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        set();
    }

    private void set(){

        try {
            lock.lock();
            System.out.println("name:"+Thread.currentThread().getName()+",set()");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Test002 test002 = new Test002();
        test002.start();
    }
}
