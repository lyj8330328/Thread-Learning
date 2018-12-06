package com.thread.basic;

/**
 * @Author: 98050
 * @Time: 2018-12-03 17:42
 * @Feature: 线程创建方式三
 */
public class Test003 {

    public static void main(String[] args) {
        System.out.println("main...........主线程开始................");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println("thread.............i:"+i);
                }
            }
        });
        thread.start();
        for (int i = 0; i < 10; i++) {
            System.out.println("main.......i:"+i);
        }
        System.out.println("main...........主线程结束................");
    }
}
