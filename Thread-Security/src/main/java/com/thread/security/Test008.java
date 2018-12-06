package com.thread.security;

/**
 * @Author: 98050
 * @Time: 2018-12-04 13:56
 * @Feature: volatile不保证共享变量的原子性
 */
public class Test008 {

    public static void main(String[] args) throws InterruptedException {
        ThreadDemo8 threadDemo8 = new ThreadDemo8();
        Thread thread = new Thread(threadDemo8);
        Thread thread2 = new Thread(threadDemo8);
        thread.start();
        thread2.start();
    }

}

class ThreadDemo8 extends Thread{

    private volatile static int count;

    @Override
    public void run() {
        addCount();
    }

    private static void addCount(){
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 10; i++) {
            count++;
            System.out.println("count="+count);
        }
    }
}
