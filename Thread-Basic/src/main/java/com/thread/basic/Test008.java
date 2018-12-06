package com.thread.basic;

/**
 * @Author: 98050
 * @Time: 2018-12-03 20:49
 * @Feature: 线程的优先级
 */
public class Test008 {
    public static void main(String[] args) {
        PriorityThread priorityThread = new PriorityThread();
        Thread thread = new Thread(priorityThread);
        Thread thread2 = new Thread(priorityThread);
        thread.start();
        //注意设置了优先级，不代表每次都一定会被执行。只是CPU调度会有限分配
        thread.setPriority(10);
        thread2.start();
    }
}

class PriorityThread implements Runnable{

    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().toString()+"-----i:"+i);
        }
    }
}
