package com.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: 98050
 * @Time: 2018-12-06 18:12
 * @Feature: tryLock()的使用
 */
public class Test2 {

    private static Lock lock = new ReentrantLock();
    public static void main(String[] args)  {
        final Test2 test = new Test2();

        new Thread(){
            @Override
            public void run() {
                test.insert(Thread.currentThread());
            };
        }.start();

        new Thread(){
            @Override
            public void run() {
                test.insert(Thread.currentThread());
            };
        }.start();
    }

    public void insert(Thread thread) {

        if (lock.tryLock()){
            try {
                System.out.println(thread.getName()+"得到了锁");
                Thread.sleep(3000);
            } catch (Exception e) {
                // TODO: handle exception
            }finally {
                System.out.println(thread.getName()+"释放了锁");
                lock.unlock();
            }
        }else {
            System.out.println(thread.getName() + "获取锁失败");
        }
    }
}
