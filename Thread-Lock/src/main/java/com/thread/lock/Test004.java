package com.thread.lock;

/**
 * @Author: 98050
 * @Time: 2018-12-13 21:47
 * @Feature: 使用悲观锁解决线程安全问题（synchronized）
 */
public class Test004 extends Thread {

    private static int COUNT = 0;
    private static Object object = new Object();

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
     * synchronized具有可重入性、保证原子性和可见性，但是对程序的执行效率有影响，不能禁止重排序，不能解决重排序问题
     * @return
     * @throws InterruptedException
     */
    private Integer getCount() throws InterruptedException {
        synchronized(object) {
            Thread.sleep(300);
            return COUNT++;
        }
    }

    public static void main(String[] args) {
        Test004 t1 = new Test004();
        Test004 t2 = new Test004();

        t1.start();
        t2.start();
    }
}
