package com.juc.concurrentlinkedqueue;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @Author: 98050
 * @Time: 2019-01-02 22:08
 * @Feature:
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {
        final ConcurrentLinkedQueue<String> concurrentLinkedQueue = new ConcurrentLinkedQueue<String>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    concurrentLinkedQueue.add(Thread.currentThread().getName()+"放入" + i);
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 10; i < 20; i++) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    concurrentLinkedQueue.add(Thread.currentThread().getName()+"放入" + i);
                }
            }
        }).start();

        Thread.sleep(1000);
        Iterator iterator = concurrentLinkedQueue.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
