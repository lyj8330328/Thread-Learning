package com.thread.lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: 98050
 * @Time: 2018-12-13 22:35
 * @Feature: 使用乐观锁解决线程安全问题(原子类)
 */
public class Test005 extends Thread {

    /**
     * 线程安全的i++
     */
    private AtomicInteger atomicInteger = new AtomicInteger();

    @Override
    public void run() {
        while (true){
            Integer count = null;
            try {
                count = getCount();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (count > 20){
                break;
            }
            System.out.println(count);
        }
    }

    /**
     *
     * @return AtomicInteger底层没有使用锁，使用CAS无锁机制
     * @throws InterruptedException
     */
    private Integer getCount() throws InterruptedException {
        Thread.sleep(300);
        return atomicInteger.incrementAndGet();
    }

    public static void main(String[] args) {
        Test004 t1 = new Test004();
        Test004 t2 = new Test004();

        t1.start();
        t2.start();
    }

}
