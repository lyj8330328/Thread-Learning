package com.thread.basic;

/**
 * @Author: 98050
 * @Time: 2018-12-03 17:55
 * @Feature: 守护线程
 */
public class Test004 {
    /**
     * 1.Java中有两种线程，一种是用户线程，另一种是守护线程。
     * 2.用户线程是指用户自定义创建的线程，主线程停止，用户线程不会停止
     * 3.守护线程当进程不存在或主线程停止，守护线程也会被停止。(gc线程)
     */

    /**
     * 用户线程 是主线程创建的线程。非守护线程
     * 守护线程 和主线程一起销毁
     * @param args
     */
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {

            public void run() {
                while (true) {
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                    System.out.println("我是子线程...");
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(100);
            } catch (Exception e) {

            }
            System.out.println("我是主线程");
        }
        System.out.println("主线程执行完毕!");
    }
}
