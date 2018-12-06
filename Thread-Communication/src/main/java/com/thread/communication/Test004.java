package com.thread.communication;

/**
 * @Author: 98050
 * @Time: 2018-12-06 16:09
 * @Feature:
 */

class MyThread extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println("MyThread..........."+i);
        }
    }
}


public class Test004 {

    public static void main(String[] args) throws InterruptedException {
        MyThread thread = new MyThread();
        thread.start();
        thread.join();
        //主线程
        for (int i = 0; i < 5; i++) {
            System.out.println("主线程............."+i);
        }
    }
}
