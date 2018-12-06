package com.thread.basic;

/**
 * @Author: 98050
 * @Time: 2018-12-03 17:59
 * @Feature: 线程睡眠
 */
public class Test005 {

    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {

            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("i:"+i);
                }
            }
        });
        thread.start();
    }
}
