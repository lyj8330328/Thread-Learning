package com.thread.basic;

/**
 * @Author: 98050
 * @Time: 2018-12-03 19:58
 * @Feature:
 */
public class Test007 {
    /**
     * 现有T1、T2、T3三个线程，你怎样保证T2在T1执行完后执行，T3在T2执行完后执行 
     */

    public static void main(String[] args) {

        final Thread thread1 = new Thread(new Runnable() {

            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println("线程1,i:"+i);
                }
            }
        });
        final Thread thread2 = new Thread(new Runnable() {

            public void run() {
                try {
                    thread1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < 10; i++) {
                    System.out.println("线程2,i:"+i);
                }
            }
        });
        Thread thread3 = new Thread(new Runnable() {

            public void run() {
                try {
                    thread2.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < 10; i++) {
                    System.out.println("线程3,i:"+i);
                }
            }
        });
        thread1.start();
        thread2.start();
        thread3.start();

    }


}

